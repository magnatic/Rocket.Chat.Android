package chat.dk.android.authentication.twofactor.presentation

import chat.dk.android.analytics.AnalyticsManager
import chat.dk.android.analytics.event.AuthenticationEvent
import chat.dk.android.authentication.presentation.AuthenticationNavigator
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.infrastructure.LocalRepository
import chat.dk.android.server.domain.GetConnectingServerInteractor
import chat.dk.android.server.domain.GetSettingsInteractor
import chat.dk.android.server.domain.PublicSettings
import chat.dk.android.server.domain.SaveAccountInteractor
import chat.dk.android.server.domain.SaveCurrentServerInteractor
import chat.dk.android.server.domain.TokenRepository
import chat.dk.android.server.domain.favicon
import chat.dk.android.server.domain.model.Account
import chat.dk.android.server.domain.wideTile
import chat.dk.android.server.infraestructure.RocketChatClientFactory
import chat.dk.android.util.extension.launchUI
import chat.dk.android.util.extensions.avatarUrl
import chat.dk.android.util.extensions.isEmail
import chat.dk.android.util.extensions.serverLogoUrl
import chat.dk.android.util.retryIO
import chat.rocket.common.RocketChatAuthException
import chat.rocket.common.RocketChatException
import chat.rocket.common.util.ifNull
import chat.rocket.core.internal.rest.login
import chat.rocket.core.internal.rest.loginWithEmail
import chat.rocket.core.internal.rest.me
import chat.rocket.core.model.Myself
import javax.inject.Inject

class TwoFAPresenter @Inject constructor(
    private val view: TwoFAView,
    private val strategy: CancelStrategy,
    private val navigator: AuthenticationNavigator,
    private val tokenRepository: TokenRepository,
    private val localRepository: LocalRepository,
    private val saveCurrentServerInteractor: SaveCurrentServerInteractor,
    private val analyticsManager: AnalyticsManager,
    private val factory: RocketChatClientFactory,
    private val saveAccountInteractor: SaveAccountInteractor,
    val serverInteractor: GetConnectingServerInteractor,
    val settingsInteractor: GetSettingsInteractor
) {
    private val currentServer = serverInteractor.get()!!
    private var settings: PublicSettings = settingsInteractor.get(serverInteractor.get()!!)

    fun authenticate(
        usernameOrEmail: String,
        password: String,
        twoFactorAuthenticationCode: String
    ) {
        launchUI(strategy) {
            val client = factory.create(currentServer)
            view.showLoading()
            try {
                // The token is saved via the client TokenProvider
                val token = retryIO("login") {
                    if (usernameOrEmail.isEmail()) {
                        client.loginWithEmail(
                            usernameOrEmail,
                            password,
                            twoFactorAuthenticationCode
                        )
                    } else {
                        client.login(usernameOrEmail, password, twoFactorAuthenticationCode)
                    }
                }
                val me = retryIO("me") { client.me() }
                saveAccount(me)
                saveCurrentServerInteractor.save(currentServer)
                tokenRepository.save(currentServer, token)
                localRepository.save(LocalRepository.CURRENT_USERNAME_KEY, me.username)
                analyticsManager.logLogin(
                    AuthenticationEvent.AuthenticationWithUserAndPassword, true
                )
                navigator.toChatList()
            } catch (exception: RocketChatException) {
                if (exception is RocketChatAuthException) {
                    view.alertInvalidTwoFactorAuthenticationCode()
                } else {
                    analyticsManager.logLogin(
                        AuthenticationEvent.AuthenticationWithUserAndPassword, false
                    )
                    exception.message?.let {
                        view.showMessage(it)
                    }.ifNull {
                        view.showGenericErrorMessage()
                    }
                }
            } finally {
                view.hideLoading()
            }
        }
    }

    private suspend fun saveAccount(me: Myself) {
        val icon = settings.favicon()?.let {
            currentServer.serverLogoUrl(it)
        }
        val logo = settings.wideTile()?.let {
            currentServer.serverLogoUrl(it)
        }
        val thumb = currentServer.avatarUrl(me.username!!)
        val account = Account(currentServer, icon, logo, me.username!!, thumb)
        saveAccountInteractor.save(account)
    }
}
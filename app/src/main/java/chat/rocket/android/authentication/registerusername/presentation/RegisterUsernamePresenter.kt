package chat.dk.android.authentication.registerusername.presentation

import chat.dk.android.analytics.AnalyticsManager
import chat.dk.android.analytics.event.AuthenticationEvent
import chat.dk.android.authentication.presentation.AuthenticationNavigator
import chat.dk.android.core.lifecycle.CancelStrategy
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
import chat.dk.android.util.extensions.serverLogoUrl
import chat.dk.android.util.retryIO
import chat.rocket.common.RocketChatException
import chat.rocket.common.model.Token
import chat.rocket.common.util.ifNull
import chat.rocket.core.RocketChatClient
import chat.rocket.core.internal.rest.updateOwnBasicInformation
import javax.inject.Inject

class RegisterUsernamePresenter @Inject constructor(
    private val view: RegisterUsernameView,
    private val strategy: CancelStrategy,
    private val navigator: AuthenticationNavigator,
    private val tokenRepository: TokenRepository,
    private val saveAccountInteractor: SaveAccountInteractor,
    private val analyticsManager: AnalyticsManager,
    private val saveCurrentServer: SaveCurrentServerInteractor,
    val serverInteractor: GetConnectingServerInteractor,
    val factory: RocketChatClientFactory,
    val settingsInteractor: GetSettingsInteractor
) {
    private val currentServer = serverInteractor.get()!!
    private val client: RocketChatClient = factory.create(currentServer)
    private var settings: PublicSettings = settingsInteractor.get(serverInteractor.get()!!)

    fun registerUsername(username: String, userId: String, authToken: String) {
        launchUI(strategy) {
            view.showLoading()
            try {
                val me = retryIO("updateOwnBasicInformation(username = $username)") {
                    client.updateOwnBasicInformation(username = username)
                }
                val registeredUsername = me.username
                if (registeredUsername != null) {
                    saveAccount(registeredUsername)
                    saveCurrentServer.save(currentServer)
                    tokenRepository.save(currentServer, Token(userId, authToken))
                    analyticsManager.logSignUp(
                        AuthenticationEvent.AuthenticationWithOauth,
                        true
                    )
                    navigator.toChatList()
                }
            } catch (exception: RocketChatException) {
                analyticsManager.logSignUp(AuthenticationEvent.AuthenticationWithOauth, false)
                exception.message?.let {
                    view.showMessage(it)
                }.ifNull {
                    view.showGenericErrorMessage()
                }
            } finally {
                view.hideLoading()
            }
        }
    }

    private suspend fun saveAccount(username: String) {
        val icon = settings.favicon()?.let {
            currentServer.serverLogoUrl(it)
        }
        val logo = settings.wideTile()?.let {
            currentServer.serverLogoUrl(it)
        }
        val thumb = currentServer.avatarUrl(username)
        val account = Account(currentServer, icon, logo, username, thumb)
        saveAccountInteractor.save(account)
    }
}
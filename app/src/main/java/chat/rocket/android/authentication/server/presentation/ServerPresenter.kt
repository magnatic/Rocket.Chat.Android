package chat.dk.android.authentication.server.presentation

import chat.dk.android.authentication.domain.model.LoginDeepLinkInfo
import chat.dk.android.authentication.presentation.AuthenticationNavigator
import chat.dk.android.core.behaviours.showMessage
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.server.domain.GetAccountsInteractor
import chat.dk.android.server.domain.GetSettingsInteractor
import chat.dk.android.server.domain.RefreshSettingsInteractor
import chat.dk.android.server.domain.SaveConnectingServerInteractor
import chat.dk.android.server.infraestructure.RocketChatClientFactory
import chat.dk.android.server.presentation.CheckServerPresenter
import chat.dk.android.util.extension.launchUI
import chat.dk.android.util.extensions.isValidUrl
import kotlinx.coroutines.experimental.DefaultDispatcher
import kotlinx.coroutines.experimental.withContext
import javax.inject.Inject

class ServerPresenter @Inject constructor(
    private val view: ServerView,
    private val strategy: CancelStrategy,
    private val navigator: AuthenticationNavigator,
    private val serverInteractor: SaveConnectingServerInteractor,
    private val refreshSettingsInteractor: RefreshSettingsInteractor,
    private val getAccountsInteractor: GetAccountsInteractor,
    val settingsInteractor: GetSettingsInteractor,
    val factory: RocketChatClientFactory
) : CheckServerPresenter(
    strategy = strategy,
    factory = factory,
    settingsInteractor = settingsInteractor,
    versionCheckView = view,
    refreshSettingsInteractor = refreshSettingsInteractor
) {

    fun checkServer(server: String) {
        if (!server.isValidUrl()) {
            view.showInvalidServerUrlMessage()
        } else {
            view.showLoading()
            setupConnectionInfo(server)
            checkServerInfo(server)
        }
    }

    fun connect(serverUrl: String) {
        connectToServer(serverUrl) {
            if (totalSocialAccountsEnabled == 0 && !isNewAccountCreationEnabled) {
                navigator.toLogin(serverUrl)
            } else {
                navigator.toLoginOptions(
                    serverUrl,
                    state,
                    facebookOauthUrl,
                    githubOauthUrl,
                    googleOauthUrl,
                    linkedinOauthUrl,
                    gitlabOauthUrl,
                    wordpressOauthUrl,
                    casLoginUrl,
                    casToken,
                    casServiceName,
                    casServiceNameTextColor,
                    casServiceButtonColor,
                    customOauthUrl,
                    customOauthServiceName,
                    customOauthServiceNameTextColor,
                    customOauthServiceButtonColor,
                    samlUrl,
                    samlToken,
                    samlServiceName,
                    samlServiceNameTextColor,
                    samlServiceButtonColor,
                    totalSocialAccountsEnabled,
                    isLoginFormEnabled,
                    isNewAccountCreationEnabled
                )
            }
        }
    }

    fun deepLink(deepLinkInfo: LoginDeepLinkInfo) {
        connectToServer(deepLinkInfo.url) {
            navigator.toLoginOptions(deepLinkInfo.url, deepLinkInfo = deepLinkInfo)
        }
    }

    private fun connectToServer(serverUrl: String, block: () -> Unit) {
        if (!serverUrl.isValidUrl()) {
            view.showInvalidServerUrlMessage()
        } else {
            launchUI(strategy) {
                // Check if we already have an account for this server...
                val account = getAccountsInteractor.get().firstOrNull { it.serverUrl == serverUrl }
                if (account != null) {
                    navigator.toChatList(serverUrl)
                    return@launchUI
                }
                view.showLoading()
                try {
                    withContext(DefaultDispatcher) {
                        // preparing next fragment before showing it
                        refreshServerAccounts()
                        checkEnabledAccounts(serverUrl)
                        checkIfLoginFormIsEnabled()
                        checkIfCreateNewAccountIsEnabled()

                        serverInteractor.save(serverUrl)

                        block()
                    }
                } catch (ex: Exception) {
                    view.showMessage(ex)
                } finally {
                    view.hideLoading()
                }
            }
        }
    }
}
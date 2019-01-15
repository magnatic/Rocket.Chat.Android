package chat.dk.android.authentication.presentation

import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.infrastructure.LocalRepository
import chat.dk.android.server.domain.GetAccountInteractor
import chat.dk.android.server.domain.GetConnectingServerInteractor
import chat.dk.android.server.domain.GetCurrentServerInteractor
import chat.dk.android.server.domain.SettingsRepository
import chat.dk.android.server.domain.TokenRepository
import chat.dk.android.util.extension.launchUI
import chat.dk.android.util.extensions.privacyPolicyUrl
import chat.dk.android.util.extensions.termsOfServiceUrl
import javax.inject.Inject

class AuthenticationPresenter @Inject constructor(
    private val strategy: CancelStrategy,
    private val navigator: AuthenticationNavigator,
    private val getCurrentServerInteractor: GetCurrentServerInteractor,
    private val getAccountInteractor: GetAccountInteractor,
    private val settingsRepository: SettingsRepository,
    private val localRepository: LocalRepository,
    private val tokenRepository: TokenRepository,
    private val serverInteractor: GetConnectingServerInteractor
) {

    fun loadCredentials(newServer: Boolean, callback: (isAuthenticated: Boolean) -> Unit) {
        launchUI(strategy) {
            val currentServer = getCurrentServerInteractor.get()
            val serverToken = currentServer?.let { tokenRepository.get(currentServer) }
            val settings = currentServer?.let { settingsRepository.get(currentServer) }
            val account = currentServer?.let { getAccountInteractor.get(currentServer) }

            account?.let {
                localRepository.save(LocalRepository.CURRENT_USERNAME_KEY, account.userName)
            }

            if (newServer || currentServer == null ||
                serverToken == null ||
                settings == null ||
                account?.userName == null
            ) {
                callback(false)
            } else {
                callback(true)
            }
        }
    }

    fun termsOfService(toolbarTitle: String) =
        serverInteractor.get()?.let { navigator.toWebPage(it.termsOfServiceUrl(), toolbarTitle) }

    fun privacyPolicy(toolbarTitle: String) =
        serverInteractor.get()?.let { navigator.toWebPage(it.privacyPolicyUrl(), toolbarTitle) }

    fun toChatList() = navigator.toChatList()
}
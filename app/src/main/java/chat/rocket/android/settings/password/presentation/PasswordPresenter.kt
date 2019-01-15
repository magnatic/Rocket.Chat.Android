package chat.dk.android.settings.password.presentation

import chat.dk.android.analytics.AnalyticsManager
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.helper.UserHelper
import chat.dk.android.server.domain.GetCurrentServerInteractor
import chat.dk.android.server.infraestructure.RocketChatClientFactory
import chat.dk.android.util.extension.launchUI
import chat.dk.android.util.retryIO
import chat.rocket.common.RocketChatException
import chat.rocket.common.util.ifNull
import chat.rocket.core.RocketChatClient
import chat.rocket.core.internal.rest.updateProfile
import javax.inject.Inject

class PasswordPresenter @Inject constructor(
    private val view: PasswordView,
    private val strategy: CancelStrategy,
    private val analyticsManager: AnalyticsManager,
    private val userHelp: UserHelper,
    serverInteractor: GetCurrentServerInteractor,
    factory: RocketChatClientFactory
) {
    private val serverUrl = serverInteractor.get()!!
    private val client: RocketChatClient = factory.create(serverUrl)

    fun updatePassword(password: String) {
        launchUI(strategy) {
            try {
                view.showLoading()
                userHelp.user()?.id?.let { userId ->
                    retryIO("updateProfile()") {
                        client.updateProfile(userId, null, null, password, null)
                    }
                    analyticsManager.logResetPassword(true)
                    view.showPasswordSuccessfullyUpdatedMessage()
                }
            } catch (exception: RocketChatException) {
                analyticsManager.logResetPassword(false)
                exception.message?.let { errorMessage ->
                    view.showPasswordFailsUpdateMessage(errorMessage)
                }
            } finally {
                view.hideLoading()
            }
        }
    }
}
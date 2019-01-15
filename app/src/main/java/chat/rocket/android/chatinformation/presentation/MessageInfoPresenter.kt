package chat.dk.android.chatinformation.presentation

import chat.dk.android.chatroom.uimodel.UiModelMapper
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.server.domain.GetCurrentServerInteractor
import chat.dk.android.server.infraestructure.ConnectionManagerFactory
import chat.dk.android.util.extension.launchUI
import chat.dk.android.util.retryIO
import chat.rocket.common.RocketChatException
import chat.rocket.core.internal.rest.getMessageReadReceipts
import timber.log.Timber
import javax.inject.Inject

class MessageInfoPresenter @Inject constructor(
    private val view: MessageInfoView,
    private val strategy: CancelStrategy,
    private val mapper: UiModelMapper,
    serverInteractor: GetCurrentServerInteractor,
    factory: ConnectionManagerFactory
) {

    private val currentServer = serverInteractor.get()!!
    private val manager = factory.create(currentServer)
    private val client = manager.client

    fun loadReadReceipts(messageId: String) {
        launchUI(strategy) {
            try {
                view.showLoading()
                val readReceipts = retryIO(description = "getMessageReadReceipts") {
                    client.getMessageReadReceipts(messageId = messageId).result
                }
                view.showReadReceipts(mapper.map(readReceipts))
            } catch (ex: RocketChatException) {
                Timber.e(ex)
                view.showGenericErrorMessage()
            } finally {
                view.hideLoading()
            }
        }
    }
}

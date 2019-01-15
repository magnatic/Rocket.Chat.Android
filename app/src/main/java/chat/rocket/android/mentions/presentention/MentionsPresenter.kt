package chat.dk.android.mentions.presentention

import chat.dk.android.chatroom.uimodel.UiModelMapper
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.server.infraestructure.RocketChatClientFactory
import chat.dk.android.util.extension.launchUI
import chat.rocket.common.RocketChatException
import chat.rocket.common.util.ifNull
import chat.rocket.core.internal.rest.getMentions
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class MentionsPresenter @Inject constructor(
    private val view: MentionsView,
    @Named("currentServer") private val currentServer: String,
    private val strategy: CancelStrategy,
    private val mapper: UiModelMapper,
    val factory: RocketChatClientFactory
) {
    private val client = factory.create(currentServer)
    private var offset: Long = 0

    /**
     * Loads all the authenticated user mentions for the given room id.
     *
     * @param roomId The id of the room to get the mentions for the authenticated user from.
     */
    fun loadMentions(roomId: String) {
        launchUI(strategy) {
            try {
                view.showLoading()
                val mentions = client.getMentions(roomId, offset, 30)
                val mentionsList = mapper.map(mentions.result, asNotReversed = true)
                view.showMentions(mentionsList)
                offset += 1 * 30
            } catch (exception: RocketChatException) {
                Timber.e(exception)
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
}
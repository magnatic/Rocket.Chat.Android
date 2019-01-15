package chat.dk.android.mentions.presentention

import chat.dk.android.chatroom.uimodel.BaseUiModel
import chat.dk.android.core.behaviours.LoadingView
import chat.dk.android.core.behaviours.MessageView

interface MentionsView : MessageView, LoadingView {

    /**
     * Shows the list of mentions for the current room.
     *
     * @param mentions The list of mentions.
     */
    fun showMentions(mentions: List<BaseUiModel<*>>)
}
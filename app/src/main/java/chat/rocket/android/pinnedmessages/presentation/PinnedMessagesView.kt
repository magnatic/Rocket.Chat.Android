package chat.dk.android.pinnedmessages.presentation

import chat.dk.android.chatroom.uimodel.BaseUiModel
import chat.dk.android.core.behaviours.LoadingView
import chat.dk.android.core.behaviours.MessageView

interface PinnedMessagesView : MessageView, LoadingView {

    /**
     * Show list of pinned messages for the current room.
     *
     * @param pinnedMessages The list of pinned messages.
     */
    fun showPinnedMessages(pinnedMessages: List<BaseUiModel<*>>)
}
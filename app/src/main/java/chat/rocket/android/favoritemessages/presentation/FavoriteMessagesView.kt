package chat.dk.android.favoritemessages.presentation

import chat.dk.android.chatroom.uimodel.BaseUiModel
import chat.dk.android.core.behaviours.LoadingView
import chat.dk.android.core.behaviours.MessageView

interface FavoriteMessagesView : MessageView, LoadingView {

    /**
     * Shows the list of favorite messages for the current room.
     *
     * @param favoriteMessages The list of favorite messages to show.
     */
    fun showFavoriteMessages(favoriteMessages: List<BaseUiModel<*>>)
}
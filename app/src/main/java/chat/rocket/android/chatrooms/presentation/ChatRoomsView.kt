package chat.dk.android.chatrooms.presentation

import chat.dk.android.core.behaviours.LoadingView
import chat.dk.android.core.behaviours.MessageView

interface ChatRoomsView : LoadingView, MessageView {
    fun showLoadingRoom(name: CharSequence)

    fun hideLoadingRoom()
}
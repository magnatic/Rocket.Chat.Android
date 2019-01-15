package chat.dk.android.chatdetails.presentation

import chat.dk.android.chatdetails.domain.ChatDetails
import chat.dk.android.core.behaviours.LoadingView
import chat.dk.android.core.behaviours.MessageView

interface ChatDetailsView: MessageView {
    fun displayDetails(room: ChatDetails)
}
package chat.dk.android.chatinformation.presentation

import chat.dk.android.chatinformation.viewmodel.ReadReceiptViewModel
import chat.dk.android.core.behaviours.LoadingView

interface MessageInfoView : LoadingView {

    fun showGenericErrorMessage()

    fun showReadReceipts(messageReceipts: List<ReadReceiptViewModel>)
}

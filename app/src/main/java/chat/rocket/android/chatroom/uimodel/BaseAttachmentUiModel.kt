package chat.dk.android.chatroom.uimodel

interface BaseAttachmentUiModel<out T> : BaseUiModel<T> {
    val attachmentUrl: String
}
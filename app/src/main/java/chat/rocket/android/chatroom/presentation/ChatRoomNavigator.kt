package chat.dk.android.chatroom.presentation

import chat.dk.android.R
import chat.dk.android.chatdetails.ui.chatDetailsIntent
import chat.dk.android.chatinformation.ui.messageInformationIntent
import chat.dk.android.chatroom.ui.ChatRoomActivity
import chat.dk.android.chatroom.ui.chatRoomIntent
import chat.dk.android.favoritemessages.ui.TAG_FAVORITE_MESSAGES_FRAGMENT
import chat.dk.android.files.ui.TAG_FILES_FRAGMENT
import chat.dk.android.members.ui.TAG_MEMBERS_FRAGMENT
import chat.dk.android.mentions.ui.TAG_MENTIONS_FRAGMENT
import chat.dk.android.pinnedmessages.ui.TAG_PINNED_MESSAGES_FRAGMENT
import chat.dk.android.server.ui.changeServerIntent
import chat.dk.android.util.extensions.addFragmentBackStack

class ChatRoomNavigator(internal val activity: ChatRoomActivity) {
    fun toChatDetails(
        chatRoomId: String,
        chatRoomType: String,
        isChatRoomSubscribed: Boolean,
        isMenuDisabled: Boolean
    ) {
        activity.startActivity(
            activity.chatDetailsIntent(
                chatRoomId,
                chatRoomType,
                isChatRoomSubscribed,
                isMenuDisabled
            )
        )
    }

    fun toNewServer() {
        activity.startActivity(activity.changeServerIntent())
        activity.finish()
    }

    fun toDirectMessage(
        chatRoomId: String,
        chatRoomName: String,
        chatRoomType: String,
        isChatRoomReadOnly: Boolean,
        chatRoomLastSeen: Long,
        isChatRoomSubscribed: Boolean,
        isChatRoomCreator: Boolean,
        isChatRoomFavorite: Boolean,
        chatRoomMessage: String
    ) {
        activity.startActivity(
            activity.chatRoomIntent(
                chatRoomId,
                chatRoomName,
                chatRoomType,
                isChatRoomReadOnly,
                chatRoomLastSeen,
                isChatRoomSubscribed,
                isChatRoomCreator,
                isChatRoomFavorite,
                chatRoomMessage
            )
        )
        activity.overridePendingTransition(R.anim.open_enter, R.anim.open_exit)
    }

    fun toMessageInformation(messageId: String) {
        activity.startActivity(activity.messageInformationIntent(messageId = messageId))
        activity.overridePendingTransition(R.anim.open_enter, R.anim.open_exit)
    }
}

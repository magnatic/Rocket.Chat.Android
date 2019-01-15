package chat.dk.android.chatdetails.presentation

import chat.dk.android.R
import chat.dk.android.chatdetails.ui.ChatDetailsActivity
import chat.dk.android.favoritemessages.ui.TAG_FAVORITE_MESSAGES_FRAGMENT
import chat.dk.android.files.ui.TAG_FILES_FRAGMENT
import chat.dk.android.members.ui.TAG_MEMBERS_FRAGMENT
import chat.dk.android.mentions.ui.TAG_MENTIONS_FRAGMENT
import chat.dk.android.pinnedmessages.ui.TAG_PINNED_MESSAGES_FRAGMENT
import chat.dk.android.util.extensions.addFragmentBackStack

class ChatDetailsNavigator(internal val activity: ChatDetailsActivity) {

    fun toMembersList(chatRoomId: String) {
        activity.addFragmentBackStack(TAG_MEMBERS_FRAGMENT, R.id.fragment_container) {
            chat.dk.android.members.ui.newInstance(chatRoomId)
        }
    }

    fun toMentions(chatRoomId: String) {
        activity.addFragmentBackStack(TAG_MENTIONS_FRAGMENT, R.id.fragment_container) {
            chat.dk.android.mentions.ui.newInstance(chatRoomId)
        }
    }

    fun toPinnedMessageList(chatRoomId: String) {
        activity.addFragmentBackStack(TAG_PINNED_MESSAGES_FRAGMENT, R.id.fragment_container) {
            chat.dk.android.pinnedmessages.ui.newInstance(chatRoomId)
        }
    }

    fun toFavoriteMessageList(chatRoomId: String) {
        activity.addFragmentBackStack(TAG_FAVORITE_MESSAGES_FRAGMENT, R.id.fragment_container) {
            chat.dk.android.favoritemessages.ui.newInstance(chatRoomId)
        }
    }

    fun toFileList(chatRoomId: String) {
        activity.addFragmentBackStack(TAG_FILES_FRAGMENT, R.id.fragment_container) {
            chat.dk.android.files.ui.newInstance(chatRoomId)
        }
    }
}

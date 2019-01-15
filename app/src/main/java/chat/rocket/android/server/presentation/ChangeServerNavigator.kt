package chat.dk.android.server.presentation

import android.content.Intent
import chat.dk.android.authentication.ui.newServerIntent
import chat.dk.android.main.ui.MainActivity
import chat.dk.android.server.ui.ChangeServerActivity
import chat.dk.android.server.ui.INTENT_CHAT_ROOM_ID

class ChangeServerNavigator (internal val activity: ChangeServerActivity) {

    fun toServerScreen() {
        activity.startActivity(activity.newServerIntent())
        activity.finish()
    }

    fun toChatRooms(chatRoomId: String? = null) {
        activity.startActivity(Intent(activity, MainActivity::class.java).also {
            it.putExtra(INTENT_CHAT_ROOM_ID, chatRoomId)
        })
        activity.finish()
    }

}
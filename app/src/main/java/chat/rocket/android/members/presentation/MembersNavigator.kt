package chat.dk.android.members.presentation

import chat.dk.android.chatdetails.ui.ChatDetailsActivity
import chat.dk.android.userdetails.ui.userDetailsIntent

class MembersNavigator(internal val activity: ChatDetailsActivity) {

    fun toMemberDetails(userId: String) {
        activity.apply {
            startActivity(this.userDetailsIntent(userId, ""))
        }
    }
}

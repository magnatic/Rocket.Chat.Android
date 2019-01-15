package chat.dk.android.profile.presentation

import chat.dk.android.core.behaviours.LoadingView
import chat.dk.android.core.behaviours.MessageView
import chat.dk.android.server.presentation.TokenView

interface ProfileView : TokenView, LoadingView, MessageView {

    /**
     * Shows the user profile.
     *
     * @param avatarUrl The user avatar URL.
     * @param name The user display name.
     * @param username The user username.
     * @param email The user email.
     */
    fun showProfile(avatarUrl: String, name: String, username: String, email: String?)

    /**
     * Reloads the user avatar (after successfully updating it).
     *
     * @param avatarUrl The user avatar URL.
     */
    fun reloadUserAvatar(avatarUrl: String)

    /**
     * Shows a profile update successfully message
     */
    fun showProfileUpdateSuccessfullyMessage()
}
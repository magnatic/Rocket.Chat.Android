package chat.dk.android.authentication.registerusername.presentation

import chat.dk.android.core.behaviours.LoadingView
import chat.dk.android.core.behaviours.MessageView

interface RegisterUsernameView : LoadingView, MessageView {

    /**
     * Enables the button to set the username if the user entered at least one character.
     */
    fun enableButtonUseThisUsername()

    /**
     * Disables the button to set the username when there is no character entered by the user.
     */
    fun disableButtonUseThisUsername()
}
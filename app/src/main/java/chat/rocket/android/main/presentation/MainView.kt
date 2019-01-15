package chat.dk.android.main.presentation

import chat.dk.android.authentication.server.presentation.VersionCheckView
import chat.dk.android.core.behaviours.MessageView
import chat.dk.android.main.uimodel.NavHeaderUiModel
import chat.dk.android.server.domain.model.Account
import chat.dk.android.server.presentation.TokenView
import chat.rocket.common.model.UserStatus

interface MainView : MessageView, VersionCheckView, TokenView {

    /**
     * Shows the current user status.
     *
     * @see [UserStatus]
     */
    fun showUserStatus(userStatus: UserStatus)

    /**
     * Setups the user account info (displayed in the nav. header)
     *
     * @param uiModel The [NavHeaderUiModel].
     */
    fun setupUserAccountInfo(uiModel: NavHeaderUiModel)

    /**
     * Setups the server account list.
     *
     * @param serverAccountList The list of server accounts.
     */
    fun setupServerAccountList(serverAccountList: List<Account>)

    fun closeServerSelection()

    fun showProgress()

    fun hideProgress()
}
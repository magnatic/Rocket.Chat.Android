package chat.dk.android.dagger.module

import chat.dk.android.about.di.AboutFragmentProvider
import chat.dk.android.authentication.di.AuthenticationModule
import chat.dk.android.authentication.login.di.LoginFragmentProvider
import chat.dk.android.authentication.loginoptions.di.LoginOptionsFragmentProvider
import chat.dk.android.authentication.onboarding.di.OnBoardingFragmentProvider
import chat.dk.android.authentication.registerusername.di.RegisterUsernameFragmentProvider
import chat.dk.android.authentication.resetpassword.di.ResetPasswordFragmentProvider
import chat.dk.android.authentication.server.di.ServerFragmentProvider
import chat.dk.android.authentication.signup.di.SignupFragmentProvider
import chat.dk.android.authentication.twofactor.di.TwoFAFragmentProvider
import chat.dk.android.authentication.ui.AuthenticationActivity
import chat.dk.android.chatdetails.di.ChatDetailsFragmentProvider
import chat.dk.android.chatdetails.di.ChatDetailsModule
import chat.dk.android.chatdetails.ui.ChatDetailsActivity
import chat.dk.android.chatinformation.di.MessageInfoFragmentProvider
import chat.dk.android.chatinformation.ui.MessageInfoActivity
import chat.dk.android.chatroom.di.ChatRoomFragmentProvider
import chat.dk.android.chatroom.di.ChatRoomModule
import chat.dk.android.chatroom.ui.ChatRoomActivity
import chat.dk.android.chatrooms.di.ChatRoomsFragmentProvider
import chat.dk.android.createchannel.di.CreateChannelProvider
import chat.dk.android.dagger.scope.PerActivity
import chat.dk.android.draw.main.di.DrawModule
import chat.dk.android.draw.main.ui.DrawingActivity
import chat.dk.android.favoritemessages.di.FavoriteMessagesFragmentProvider
import chat.dk.android.files.di.FilesFragmentProvider
import chat.dk.android.main.di.MainModule
import chat.dk.android.main.ui.MainActivity
import chat.dk.android.members.di.MembersFragmentProvider
import chat.dk.android.mentions.di.MentionsFragmentProvider
import chat.dk.android.pinnedmessages.di.PinnedMessagesFragmentProvider
import chat.dk.android.preferences.di.PreferencesFragmentProvider
import chat.dk.android.profile.di.ProfileFragmentProvider
import chat.dk.android.server.di.ChangeServerModule
import chat.dk.android.server.ui.ChangeServerActivity
import chat.dk.android.settings.di.SettingsFragmentProvider
import chat.dk.android.settings.password.di.PasswordFragmentProvider
import chat.dk.android.settings.password.ui.PasswordActivity
import chat.dk.android.userdetails.di.UserDetailsModule
import chat.dk.android.userdetails.ui.UserDetailsActivity
import chat.dk.android.webview.adminpanel.di.AdminPanelWebViewFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @PerActivity
    @ContributesAndroidInjector(
        modules = [AuthenticationModule::class,
            OnBoardingFragmentProvider::class,
            ServerFragmentProvider::class,
            LoginOptionsFragmentProvider::class,
            LoginFragmentProvider::class,
            RegisterUsernameFragmentProvider::class,
            ResetPasswordFragmentProvider::class,
            SignupFragmentProvider::class,
            TwoFAFragmentProvider::class
        ]
    )
    abstract fun bindAuthenticationActivity(): AuthenticationActivity

    @PerActivity
    @ContributesAndroidInjector(
        modules = [MainModule::class,
            ChatRoomsFragmentProvider::class,
            CreateChannelProvider::class,
            ProfileFragmentProvider::class,
            SettingsFragmentProvider::class,
            AboutFragmentProvider::class,
            PreferencesFragmentProvider::class,
            AdminPanelWebViewFragmentProvider::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(
        modules = [
            ChatRoomModule::class,
            ChatRoomFragmentProvider::class
        ]
    )
    abstract fun bindChatRoomActivity(): ChatRoomActivity

    @PerActivity
    @ContributesAndroidInjector(
        modules = [
            ChatDetailsModule::class,
            ChatDetailsFragmentProvider::class,
            MembersFragmentProvider::class,
            MentionsFragmentProvider::class,
            PinnedMessagesFragmentProvider::class,
            FavoriteMessagesFragmentProvider::class,
            FilesFragmentProvider::class
        ]
    )
    abstract fun bindChatDetailsActivity(): ChatDetailsActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [PasswordFragmentProvider::class])
    abstract fun bindPasswordActivity(): PasswordActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [ChangeServerModule::class])
    abstract fun bindChangeServerActivity(): ChangeServerActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MessageInfoFragmentProvider::class])
    abstract fun bindMessageInfoActiviy(): MessageInfoActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [DrawModule::class])
    abstract fun bindDrawingActivity(): DrawingActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [UserDetailsModule::class])
    abstract fun bindUserDetailsActivity(): UserDetailsActivity
}

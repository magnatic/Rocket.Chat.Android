package chat.dk.android.members.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.chatdetails.ui.ChatDetailsActivity
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.members.presentation.MembersNavigator
import chat.dk.android.members.presentation.MembersView
import chat.dk.android.members.ui.MembersFragment
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.Job

@Module
class MembersFragmentModule {

    @Provides
    @PerFragment
    fun membersView(frag: MembersFragment): MembersView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideChatRoomNavigator(activity: ChatDetailsActivity) = MembersNavigator(activity)

    @Provides
    @PerFragment
    fun provideJob() = Job()

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: MembersFragment): LifecycleOwner {
        return frag
    }

    @Provides
    @PerFragment
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy {
        return CancelStrategy(owner, jobs)
    }
}
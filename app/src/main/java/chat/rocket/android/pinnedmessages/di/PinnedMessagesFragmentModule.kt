package chat.dk.android.pinnedmessages.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.pinnedmessages.presentation.PinnedMessagesView
import chat.dk.android.pinnedmessages.ui.PinnedMessagesFragment
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.Job

@Module
class PinnedMessagesFragmentModule {

    @Provides
    @PerFragment
    fun providePinnedMessagesView(frag: PinnedMessagesFragment): PinnedMessagesView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideJob() = Job()

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: PinnedMessagesFragment): LifecycleOwner {
        return frag
    }

    @Provides
    @PerFragment
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy {
        return CancelStrategy(owner, jobs)
    }
}
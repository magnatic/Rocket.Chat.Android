package chat.dk.android.mentions.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.mentions.presentention.MentionsView
import chat.dk.android.mentions.ui.MentionsFragment
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.Job

@Module
class MentionsFragmentModule {

    @Provides
    @PerFragment
    fun provideMentionsView(frag: MentionsFragment): MentionsView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideJob() = Job()

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: MentionsFragment): LifecycleOwner {
        return frag
    }

    @Provides
    @PerFragment
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy {
        return CancelStrategy(owner, jobs)
    }
}
package chat.dk.android.chatinformation.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.chatinformation.presentation.MessageInfoView
import chat.dk.android.chatinformation.ui.MessageInfoFragment
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.Job

@Module
class MessageInfoFragmentModule {

    @Provides
    @PerFragment
    fun provideJob() = Job()

    @Provides
    @PerFragment
    fun messageInfoView(frag: MessageInfoFragment): MessageInfoView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: MessageInfoFragment): LifecycleOwner {
        return frag
    }

    @Provides
    @PerFragment
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy {
        return CancelStrategy(owner, jobs)
    }
}

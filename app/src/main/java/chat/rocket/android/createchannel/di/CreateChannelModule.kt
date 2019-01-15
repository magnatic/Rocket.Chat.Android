package chat.dk.android.createchannel.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.createchannel.presentation.CreateChannelView
import chat.dk.android.createchannel.ui.CreateChannelFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class CreateChannelModule {

    @Provides
    @PerFragment
    fun createChannelView(fragment: CreateChannelFragment): CreateChannelView {
        return fragment
    }

    @Provides
    @PerFragment
    fun provideLifecycleOwner(fragment: CreateChannelFragment): LifecycleOwner {
        return fragment
    }
}
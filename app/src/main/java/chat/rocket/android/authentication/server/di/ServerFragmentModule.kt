package chat.dk.android.authentication.server.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.authentication.server.presentation.ServerView
import chat.dk.android.authentication.server.ui.ServerFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class ServerFragmentModule {

    @Provides
    @PerFragment
    fun serverView(frag: ServerFragment): ServerView = frag

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: ServerFragment): LifecycleOwner = frag
}
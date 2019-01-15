package chat.dk.android.authentication.registerusername.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.authentication.registerusername.presentation.RegisterUsernameView
import chat.dk.android.authentication.registerusername.ui.RegisterUsernameFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class RegisterUsernameFragmentModule {

    @Provides
    @PerFragment
    fun registerUsernameView(frag: RegisterUsernameFragment): RegisterUsernameView = frag

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: RegisterUsernameFragment): LifecycleOwner = frag
}
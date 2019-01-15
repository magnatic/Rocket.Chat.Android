package chat.dk.android.authentication.loginoptions.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.authentication.loginoptions.presentation.LoginOptionsView
import chat.dk.android.authentication.loginoptions.ui.LoginOptionsFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class LoginOptionsFragmentModule {

    @Provides
    @PerFragment
    fun loginOptionsView(frag: LoginOptionsFragment): LoginOptionsView = frag

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: LoginOptionsFragment): LifecycleOwner = frag
}
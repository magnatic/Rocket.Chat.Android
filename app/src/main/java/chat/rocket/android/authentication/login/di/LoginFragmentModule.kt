package chat.dk.android.authentication.login.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.authentication.login.presentation.LoginView
import chat.dk.android.authentication.login.ui.LoginFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class LoginFragmentModule {

    @Provides
    @PerFragment
    fun loginView(frag: LoginFragment): LoginView = frag

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: LoginFragment): LifecycleOwner = frag
}
package chat.dk.android.authentication.signup.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.authentication.signup.presentation.SignupView
import chat.dk.android.authentication.signup.ui.SignupFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class SignupFragmentModule {

    @Provides
    @PerFragment
    fun signupView(frag: SignupFragment): SignupView = frag

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: SignupFragment): LifecycleOwner = frag
}
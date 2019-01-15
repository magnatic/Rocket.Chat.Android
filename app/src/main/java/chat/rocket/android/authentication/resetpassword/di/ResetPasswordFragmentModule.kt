package chat.dk.android.authentication.resetpassword.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.authentication.resetpassword.presentation.ResetPasswordView
import chat.dk.android.authentication.resetpassword.ui.ResetPasswordFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class ResetPasswordFragmentModule {

    @Provides
    @PerFragment
    fun resetPasswordView(frag: ResetPasswordFragment): ResetPasswordView = frag

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: ResetPasswordFragment): LifecycleOwner = frag
}
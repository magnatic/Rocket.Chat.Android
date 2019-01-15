package chat.dk.android.authentication.signup.di

import chat.dk.android.authentication.signup.ui.SignupFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SignupFragmentProvider {

    @ContributesAndroidInjector(modules = [SignupFragmentModule::class])
    @PerFragment
    abstract fun provideSignupFragment(): SignupFragment
}
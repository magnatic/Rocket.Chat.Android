package chat.dk.android.authentication.login.di

import chat.dk.android.authentication.login.ui.LoginFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class LoginFragmentProvider {

    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    @PerFragment
    abstract fun provideLoginFragment(): LoginFragment
}
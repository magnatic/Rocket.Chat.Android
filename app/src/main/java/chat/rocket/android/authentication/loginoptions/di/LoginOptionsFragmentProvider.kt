package chat.dk.android.authentication.loginoptions.di

import chat.dk.android.authentication.loginoptions.ui.LoginOptionsFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class LoginOptionsFragmentProvider {

    @ContributesAndroidInjector(modules = [LoginOptionsFragmentModule::class])
    @PerFragment
    abstract fun providesLoginOptionFragment(): LoginOptionsFragment
}
package chat.dk.android.authentication.registerusername.di

import chat.dk.android.authentication.registerusername.ui.RegisterUsernameFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RegisterUsernameFragmentProvider {

    @ContributesAndroidInjector(modules = [RegisterUsernameFragmentModule::class])
    @PerFragment
    abstract fun provideRegisterUsernameFragment(): RegisterUsernameFragment
}
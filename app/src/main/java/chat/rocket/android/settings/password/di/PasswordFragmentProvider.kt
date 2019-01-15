package chat.dk.android.settings.password.di

import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.settings.password.ui.PasswordFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PasswordFragmentProvider {
    @ContributesAndroidInjector(modules = [PasswordFragmentModule::class])
    @PerFragment
    abstract fun providePasswordFragment(): PasswordFragment
}
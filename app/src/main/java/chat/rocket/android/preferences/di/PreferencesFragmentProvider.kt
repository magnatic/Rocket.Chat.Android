package chat.dk.android.preferences.di

import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.preferences.ui.PreferencesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PreferencesFragmentProvider {

    @ContributesAndroidInjector(modules = [PreferencesFragmentModule::class])
    @PerFragment
    abstract fun providePreferencesFragment(): PreferencesFragment
}
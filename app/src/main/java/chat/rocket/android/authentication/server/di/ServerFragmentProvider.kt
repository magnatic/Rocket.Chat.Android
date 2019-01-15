package chat.dk.android.authentication.server.di

import chat.dk.android.authentication.server.ui.ServerFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServerFragmentProvider {

    @ContributesAndroidInjector(modules = [ServerFragmentModule::class])
    @PerFragment
    abstract fun provideServerFragment(): ServerFragment
}
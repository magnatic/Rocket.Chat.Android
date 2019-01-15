package chat.dk.android.createchannel.di

import chat.dk.android.createchannel.ui.CreateChannelFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CreateChannelProvider {

    @ContributesAndroidInjector(modules = [CreateChannelModule::class])
    @PerFragment
    abstract fun provideCreateChannelFragment(): CreateChannelFragment
}
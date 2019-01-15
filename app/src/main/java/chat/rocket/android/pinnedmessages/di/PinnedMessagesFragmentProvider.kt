package chat.dk.android.pinnedmessages.di

import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.pinnedmessages.ui.PinnedMessagesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PinnedMessagesFragmentProvider {

    @ContributesAndroidInjector(modules = [PinnedMessagesFragmentModule::class])
    @PerFragment
    abstract fun providePinnedMessageFragment(): PinnedMessagesFragment
}
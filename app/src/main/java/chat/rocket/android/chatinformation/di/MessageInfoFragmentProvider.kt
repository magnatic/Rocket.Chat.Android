package chat.dk.android.chatinformation.di

import chat.dk.android.chatinformation.ui.MessageInfoFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MessageInfoFragmentProvider {

    @ContributesAndroidInjector(modules = [MessageInfoFragmentModule::class])
    @PerFragment
    abstract fun provideMessageInfoFragment(): MessageInfoFragment
}

package chat.dk.android.chatdetails.di

import chat.dk.android.chatdetails.ui.ChatDetailsFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChatDetailsFragmentProvider {

    @ContributesAndroidInjector(modules = [ChatDetailsFragmentModule::class])
    @PerFragment
    abstract fun providesChatDetailsFragment(): ChatDetailsFragment
}
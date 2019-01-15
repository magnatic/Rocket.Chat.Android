package chat.dk.android.chatrooms.di

import chat.dk.android.chatrooms.ui.ChatRoomsFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChatRoomsFragmentProvider {

    @ContributesAndroidInjector(modules = [ChatRoomsFragmentModule::class])
    @PerFragment
    abstract fun provideChatRoomsFragment(): ChatRoomsFragment
}
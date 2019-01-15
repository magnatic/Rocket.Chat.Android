package chat.dk.android.chatroom.di

import chat.dk.android.chatroom.ui.ChatRoomFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChatRoomFragmentProvider {

    @ContributesAndroidInjector(modules = [ChatRoomFragmentModule::class])
    @PerFragment
    abstract fun provideChatRoomFragment(): ChatRoomFragment
}
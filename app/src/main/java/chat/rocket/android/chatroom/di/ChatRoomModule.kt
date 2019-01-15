package chat.dk.android.chatroom.di

import chat.dk.android.chatroom.presentation.ChatRoomNavigator
import chat.dk.android.chatroom.ui.ChatRoomActivity
import chat.dk.android.dagger.scope.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ChatRoomModule {
    @Provides
    @PerActivity
    fun provideChatRoomNavigator(activity: ChatRoomActivity) = ChatRoomNavigator(activity)
}
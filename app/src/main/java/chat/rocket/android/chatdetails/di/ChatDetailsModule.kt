package chat.dk.android.chatdetails.di

import chat.dk.android.chatdetails.presentation.ChatDetailsNavigator
import chat.dk.android.chatdetails.ui.ChatDetailsActivity
import chat.dk.android.dagger.scope.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ChatDetailsModule {

    @Provides
    @PerActivity
    fun providesNavigator(activity: ChatDetailsActivity) = ChatDetailsNavigator(activity)
}
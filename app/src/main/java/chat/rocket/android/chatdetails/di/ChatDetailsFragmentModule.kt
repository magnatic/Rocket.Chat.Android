package chat.dk.android.chatdetails.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.chatdetails.presentation.ChatDetailsView
import chat.dk.android.chatdetails.ui.ChatDetailsFragment
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.db.ChatRoomDao
import chat.dk.android.db.DatabaseManager
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.Job

@Module
class ChatDetailsFragmentModule {

    @Provides
    @PerFragment
    fun provideJob() = Job()

    @Provides
    @PerFragment
    fun chatDetailsView(frag: ChatDetailsFragment): ChatDetailsView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideChatRoomDao(manager: DatabaseManager): ChatRoomDao = manager.chatRoomDao()

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: ChatDetailsFragment): LifecycleOwner {
        return frag
    }

    @Provides
    @PerFragment
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy {
        return CancelStrategy(owner, jobs)
    }
}
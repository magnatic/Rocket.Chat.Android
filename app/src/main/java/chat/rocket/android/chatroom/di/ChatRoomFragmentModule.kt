package chat.dk.android.chatroom.di

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import chat.dk.android.chatroom.presentation.ChatRoomView
import chat.dk.android.chatroom.ui.ChatRoomFragment
import chat.dk.android.chatrooms.adapter.RoomUiModelMapper
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.db.ChatRoomDao
import chat.dk.android.db.DatabaseManager
import chat.dk.android.db.UserDao
import chat.dk.android.server.domain.GetCurrentUserInteractor
import chat.dk.android.server.domain.PermissionsInteractor
import chat.dk.android.server.domain.SettingsRepository
import chat.dk.android.server.domain.TokenRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.Job
import javax.inject.Named

@Module
class ChatRoomFragmentModule {

    @Provides
    @PerFragment
    fun provideJob() = Job()

    @Provides
    @PerFragment
    fun chatRoomView(frag: ChatRoomFragment): ChatRoomView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: ChatRoomFragment): LifecycleOwner {
        return frag
    }

    @Provides
    @PerFragment
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy {
        return CancelStrategy(owner, jobs)
    }

    @Provides
    @PerFragment
    fun provideChatRoomDao(manager: DatabaseManager): ChatRoomDao = manager.chatRoomDao()

    @Provides
    @PerFragment
    fun provideUserDao(manager: DatabaseManager): UserDao = manager.userDao()

    @Provides
    @PerFragment
    fun provideGetCurrentUserInteractor(
        tokenRepository: TokenRepository,
        @Named("currentServer") serverUrl: String,
        userDao: UserDao
    ): GetCurrentUserInteractor {
        return GetCurrentUserInteractor(tokenRepository, serverUrl, userDao)
    }

    @Provides
    @PerFragment
    fun provideRoomMapper(
        context: Application,
        repository: SettingsRepository,
        userInteractor: GetCurrentUserInteractor,
        @Named("currentServer") serverUrl: String,
        permissionsInteractor: PermissionsInteractor
    ): RoomUiModelMapper {
        return RoomUiModelMapper(context, repository.get(serverUrl), userInteractor, serverUrl, permissionsInteractor)
    }
}

package chat.dk.android.chatrooms.di

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import chat.dk.android.chatrooms.adapter.RoomUiModelMapper
import chat.dk.android.chatrooms.domain.FetchChatRoomsInteractor
import chat.dk.android.chatrooms.presentation.ChatRoomsView
import chat.dk.android.chatrooms.ui.ChatRoomsFragment
import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.db.ChatRoomDao
import chat.dk.android.db.DatabaseManager
import chat.dk.android.db.UserDao
import chat.dk.android.infrastructure.LocalRepository
import chat.dk.android.server.domain.GetCurrentUserInteractor
import chat.dk.android.server.domain.PermissionsInteractor
import chat.dk.android.server.domain.PublicSettings
import chat.dk.android.server.domain.SettingsRepository
import chat.dk.android.server.domain.TokenRepository
import chat.dk.android.server.infraestructure.ConnectionManager
import chat.dk.android.server.infraestructure.ConnectionManagerFactory
import chat.dk.android.server.infraestructure.RocketChatClientFactory
import chat.rocket.core.RocketChatClient
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ChatRoomsFragmentModule {

    @Provides
    @PerFragment
    fun chatRoomsView(frag: ChatRoomsFragment): ChatRoomsView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: ChatRoomsFragment): LifecycleOwner {
        return frag
    }

    @Provides
    @PerFragment
    fun provideRocketChatClient(
        factory: RocketChatClientFactory,
        @Named("currentServer") currentServer: String
    ): RocketChatClient {
        return factory.create(currentServer)
    }

    @Provides
    @PerFragment
    fun provideChatRoomDao(manager: DatabaseManager): ChatRoomDao = manager.chatRoomDao()

    @Provides
    @PerFragment
    fun provideUserDao(manager: DatabaseManager): UserDao = manager.userDao()

    @Provides
    @PerFragment
    fun provideConnectionManager(
        factory: ConnectionManagerFactory,
        @Named("currentServer") currentServer: String
    ): ConnectionManager {
        return factory.create(currentServer)
    }

    @Provides
    @PerFragment
    fun provideFetchChatRoomsInteractor(
        client: RocketChatClient,
        dbManager: DatabaseManager
    ): FetchChatRoomsInteractor {
        return FetchChatRoomsInteractor(client, dbManager)
    }

    @Provides
    @PerFragment
    fun providePublicSettings(
        repository: SettingsRepository,
        @Named("currentServer") currentServer: String
    ): PublicSettings {
        return repository.get(currentServer)
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

    @Provides
    @PerFragment
    fun provideGetCurrentUserInteractor(
        tokenRepository: TokenRepository,
        @Named("currentServer") serverUrl: String,
        userDao: UserDao
    ): GetCurrentUserInteractor {
        return GetCurrentUserInteractor(tokenRepository, serverUrl, userDao)
    }
}
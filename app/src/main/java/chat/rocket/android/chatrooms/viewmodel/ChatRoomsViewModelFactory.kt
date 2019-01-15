package chat.dk.android.chatrooms.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import chat.dk.android.chatrooms.adapter.RoomUiModelMapper
import chat.dk.android.chatrooms.domain.FetchChatRoomsInteractor
import chat.dk.android.chatrooms.infrastructure.ChatRoomsRepository
import chat.dk.android.server.infraestructure.ConnectionManager
import javax.inject.Inject

class ChatRoomsViewModelFactory @Inject constructor(
    private val connectionManager: ConnectionManager,
    private val interactor: FetchChatRoomsInteractor,
    private val repository: ChatRoomsRepository,
    private val mapper: RoomUiModelMapper
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            ChatRoomsViewModel(connectionManager, interactor, repository, mapper) as T
}
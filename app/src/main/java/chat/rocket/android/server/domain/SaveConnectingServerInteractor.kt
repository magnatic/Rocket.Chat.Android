package chat.dk.android.server.domain

import chat.dk.android.dagger.qualifier.ForAuthentication
import javax.inject.Inject

class SaveConnectingServerInteractor @Inject constructor(
    @ForAuthentication private val repository: CurrentServerRepository
) {
    fun save(url: String) = repository.save(url)
}
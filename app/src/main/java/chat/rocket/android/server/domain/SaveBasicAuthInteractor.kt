package chat.dk.android.server.domain

import chat.dk.android.server.domain.model.BasicAuth
import javax.inject.Inject

class SaveBasicAuthInteractor @Inject constructor(val repository: BasicAuthRepository) {
    fun save(basicAuth: BasicAuth) = repository.save(basicAuth)
}

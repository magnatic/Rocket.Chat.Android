package chat.dk.android.server.domain

import chat.dk.android.server.domain.model.BasicAuth

interface BasicAuthRepository {
    fun save(basicAuth: BasicAuth)
    fun load(): List<BasicAuth>
}

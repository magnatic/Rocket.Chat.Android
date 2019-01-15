package chat.dk.android.server.domain

import chat.dk.android.db.UserDao
import chat.dk.android.db.model.UserEntity

class GetCurrentUserInteractor(
    private val tokenRepository: TokenRepository,
    private val currentServer: String,
    private val userDao: UserDao
) {
    fun get(): UserEntity? {
        return tokenRepository.get(currentServer)?.let {
            userDao.getUser(it.userId)
        }
    }

}

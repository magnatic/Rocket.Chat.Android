package chat.dk.android.server.domain

import chat.dk.android.server.domain.model.Account
import javax.inject.Inject

class SaveAccountInteractor @Inject constructor(val repository: AccountsRepository) {
    suspend fun save(account: Account) = repository.save(account)
}
package chat.dk.android.push.worker

import androidx.work.Worker
import chat.dk.android.dagger.injector.AndroidWorkerInjection
import chat.dk.android.extensions.await
import chat.dk.android.infrastructure.LocalRepository
import chat.dk.android.server.domain.GetAccountsInteractor
import chat.dk.android.server.infraestructure.RocketChatClientFactory
import chat.dk.android.util.extensions.registerPushToken
import chat.rocket.common.util.ifNull
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.coroutines.experimental.runBlocking
import timber.log.Timber
import javax.inject.Inject

class TokenRegistrationWorker : Worker() {

    @Inject
    lateinit var factory: RocketChatClientFactory
    @Inject
    lateinit var getAccountsInteractor: GetAccountsInteractor
    @Inject
    lateinit var localRepository: LocalRepository

    override fun doWork(): Result {
        AndroidWorkerInjection.inject(this)

        runBlocking {
            val token = inputData.getString("token") ?: refreshToken()

            token?.let { fcmToken ->
                localRepository.save(LocalRepository.KEY_PUSH_TOKEN, fcmToken)
                factory.registerPushToken(fcmToken, getAccountsInteractor.get())
            }.ifNull {
                Timber.d("Unavailable FCM Token...")
            }
        }

        return Result.SUCCESS
    }

    private fun refreshToken(): String? {
        return runBlocking {
            try {
                FirebaseInstanceId.getInstance().instanceId.await().token
            } catch (ex: Exception) {
                Timber.e(ex, "Error refreshing Firebase TOKEN")
                null
            }
        }
    }
}
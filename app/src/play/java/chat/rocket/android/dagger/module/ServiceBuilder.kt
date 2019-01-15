package chat.dk.android.dagger.module

import androidx.work.Worker
import chat.dk.android.chatroom.di.MessageServiceProvider
import chat.dk.android.chatroom.service.MessageService
import chat.dk.android.dagger.qualifier.WorkerKey
import chat.dk.android.push.FirebaseMessagingService
import chat.dk.android.push.di.FirebaseMessagingServiceProvider
import chat.dk.android.push.di.TokenRegistrationSubComponent
import chat.dk.android.push.worker.TokenRegistrationWorker
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [TokenRegistrationSubComponent::class])
abstract class ServiceBuilder {

    @ContributesAndroidInjector(modules = [FirebaseMessagingServiceProvider::class])
    abstract fun bindGcmListenerService(): FirebaseMessagingService

    @ContributesAndroidInjector(modules = [MessageServiceProvider::class])
    abstract fun bindMessageService(): MessageService

    @Binds
    @IntoMap
    @WorkerKey(TokenRegistrationWorker::class)
    abstract fun bindTokenRegistrationWorkerFactory(
        builder: TokenRegistrationSubComponent.Builder
    ): AndroidInjector.Factory<out Worker>
}
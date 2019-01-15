package chat.dk.android.dagger

import android.app.Application
import chat.dk.android.app.RocketChatApplication
import chat.dk.android.chatroom.service.MessageService
import chat.dk.android.dagger.module.ActivityBuilder
import chat.dk.android.dagger.module.AndroidWorkerInjectionModule
import chat.dk.android.dagger.module.AppModule
import chat.dk.android.dagger.module.ReceiverBuilder
import chat.dk.android.dagger.module.ServiceBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    AppModule::class, ActivityBuilder::class, ServiceBuilder::class, ReceiverBuilder::class,
    AndroidWorkerInjectionModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: RocketChatApplication)

    fun inject(service: MessageService)
}

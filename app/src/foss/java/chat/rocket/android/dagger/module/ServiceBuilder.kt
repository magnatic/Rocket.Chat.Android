package chat.dk.android.dagger.module

import chat.dk.android.chatroom.di.MessageServiceProvider
import chat.dk.android.chatroom.service.MessageService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class ServiceBuilder {
    @ContributesAndroidInjector(modules = [MessageServiceProvider::class])
    abstract fun bindMessageService(): MessageService
}

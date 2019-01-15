package chat.dk.android.chatroom.di

import chat.dk.android.chatroom.service.MessageService
import chat.dk.android.dagger.module.AppModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class MessageServiceProvider {
    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun provideMessageService(): MessageService
}
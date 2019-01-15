package chat.dk.android.push.di

import chat.dk.android.dagger.module.AppModule
import chat.dk.android.push.FirebaseMessagingService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class FirebaseMessagingServiceProvider {
    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun provideFirebaseMessagingService(): FirebaseMessagingService
}
package chat.dk.android.dagger.module

import chat.dk.android.push.DeleteReceiver
import chat.dk.android.push.DirectReplyReceiver
import chat.dk.android.push.DirectReplyReceiverProvider
import chat.dk.android.push.di.DeleteReceiverProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ReceiverBuilder {

    @ContributesAndroidInjector(modules = [DeleteReceiverProvider::class])
    abstract fun bindDeleteReceiver(): DeleteReceiver

    @ContributesAndroidInjector(modules = [DirectReplyReceiverProvider::class])
    abstract fun bindDirectReplyReceiver(): DirectReplyReceiver
}
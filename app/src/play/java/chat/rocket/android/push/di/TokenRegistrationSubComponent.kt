package chat.dk.android.push.di

import chat.dk.android.push.worker.TokenRegistrationWorker
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface TokenRegistrationSubComponent : AndroidInjector<TokenRegistrationWorker> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TokenRegistrationWorker>()
}
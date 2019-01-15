package chat.dk.android.authentication.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.authentication.presentation.AuthenticationNavigator
import chat.dk.android.authentication.ui.AuthenticationActivity
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.dagger.scope.PerActivity
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.Job

@Module
class AuthenticationModule {

    @Provides
    @PerActivity
    fun provideAuthenticationNavigator(activity: AuthenticationActivity) =
        AuthenticationNavigator(activity)

    @Provides
    @PerActivity
    fun provideJob() = Job()

    @Provides
    @PerActivity
    fun provideLifecycleOwner(activity: AuthenticationActivity): LifecycleOwner = activity

    @Provides
    @PerActivity
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy =
        CancelStrategy(owner, jobs)
}
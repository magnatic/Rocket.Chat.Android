package chat.dk.android.main.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.dagger.scope.PerActivity
import chat.dk.android.main.presentation.MainNavigator
import chat.dk.android.main.presentation.MainView
import chat.dk.android.main.ui.MainActivity
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.Job

@Module
class MainModule {

    @Provides
    @PerActivity
    fun provideJob() = Job()

    @Provides
    @PerActivity
    fun provideMainNavigator(activity: MainActivity) = MainNavigator(activity)

    @Provides
    fun provideMainView(activity: MainActivity): MainView = activity

    @Provides
    fun provideLifecycleOwner(activity: MainActivity): LifecycleOwner = activity

    @Provides
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy =
        CancelStrategy(owner, jobs)
}
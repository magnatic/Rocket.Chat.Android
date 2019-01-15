package chat.dk.android.favoritemessages.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.core.lifecycle.CancelStrategy
import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.db.DatabaseManager
import chat.dk.android.db.DatabaseManagerFactory
import chat.dk.android.favoritemessages.presentation.FavoriteMessagesView
import chat.dk.android.favoritemessages.ui.FavoriteMessagesFragment
import chat.dk.android.server.domain.GetCurrentServerInteractor
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.Job
import javax.inject.Named

@Module
class FavoriteMessagesFragmentModule {

    @Provides
    @PerFragment
    fun provideFavoriteMessagesView(frag: FavoriteMessagesFragment): FavoriteMessagesView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideJob() = Job()

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: FavoriteMessagesFragment): LifecycleOwner {
        return frag
    }

    @Provides
    @PerFragment
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy {
        return CancelStrategy(owner, jobs)
    }
}
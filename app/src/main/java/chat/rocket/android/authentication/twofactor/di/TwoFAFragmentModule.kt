package chat.dk.android.authentication.twofactor.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.authentication.twofactor.presentation.TwoFAView
import chat.dk.android.authentication.twofactor.ui.TwoFAFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class TwoFAFragmentModule {

    @Provides
    @PerFragment
    fun loginView(frag: TwoFAFragment): TwoFAView = frag

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: TwoFAFragment): LifecycleOwner = frag
}

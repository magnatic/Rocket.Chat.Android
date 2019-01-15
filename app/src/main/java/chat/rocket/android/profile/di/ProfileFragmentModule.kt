package chat.dk.android.profile.di

import androidx.lifecycle.LifecycleOwner
import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.profile.presentation.ProfileView
import chat.dk.android.profile.ui.ProfileFragment
import dagger.Module
import dagger.Provides

@Module
class ProfileFragmentModule {

    @Provides
    @PerFragment
    fun profileView(frag: ProfileFragment): ProfileView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: ProfileFragment): LifecycleOwner {
        return frag
    }
}
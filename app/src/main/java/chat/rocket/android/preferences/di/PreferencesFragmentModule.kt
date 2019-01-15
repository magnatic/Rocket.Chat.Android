package chat.dk.android.preferences.di

import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.preferences.presentation.PreferencesView
import chat.dk.android.preferences.ui.PreferencesFragment
import dagger.Module
import dagger.Provides

@Module
class PreferencesFragmentModule {

    @Provides
    @PerFragment
    fun preferencesView(frag: PreferencesFragment): PreferencesView {
        return frag
    }
}
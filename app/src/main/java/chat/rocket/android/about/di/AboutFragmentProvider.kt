package chat.dk.android.about.di

import chat.dk.android.about.ui.AboutFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AboutFragmentProvider {

    @ContributesAndroidInjector()
    abstract fun provideAboutFragment(): AboutFragment
}

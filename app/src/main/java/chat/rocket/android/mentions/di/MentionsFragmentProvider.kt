package chat.dk.android.mentions.di

import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.mentions.ui.MentionsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MentionsFragmentProvider {

    @ContributesAndroidInjector(modules = [MentionsFragmentModule::class])
    @PerFragment
    abstract fun provideMentionsFragment(): MentionsFragment
}
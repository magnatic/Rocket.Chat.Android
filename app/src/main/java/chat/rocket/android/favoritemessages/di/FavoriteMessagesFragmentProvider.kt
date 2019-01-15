package chat.dk.android.favoritemessages.di

import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.favoritemessages.ui.FavoriteMessagesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteMessagesFragmentProvider {

    @ContributesAndroidInjector(modules = [FavoriteMessagesFragmentModule::class])
    @PerFragment
    abstract fun provideFavoriteMessageFragment(): FavoriteMessagesFragment
}
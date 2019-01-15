package chat.dk.android.profile.di

import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.profile.ui.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProfileFragmentProvider {

    @ContributesAndroidInjector(modules = [ProfileFragmentModule::class])
    @PerFragment
    abstract fun provideProfileFragment(): ProfileFragment
}
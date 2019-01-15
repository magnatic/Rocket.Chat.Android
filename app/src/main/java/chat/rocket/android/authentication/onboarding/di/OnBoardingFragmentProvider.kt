package chat.dk.android.authentication.onboarding.di

import chat.dk.android.authentication.onboarding.ui.OnBoardingFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class OnBoardingFragmentProvider {

    @ContributesAndroidInjector(modules = [OnBoardingFragmentModule::class])
    @PerFragment
    abstract fun provideOnBoardingFragment(): OnBoardingFragment
}
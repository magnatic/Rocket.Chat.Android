package chat.dk.android.authentication.twofactor.di

import chat.dk.android.authentication.twofactor.ui.TwoFAFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class TwoFAFragmentProvider {

    @ContributesAndroidInjector(modules = [TwoFAFragmentModule::class])
    @PerFragment
    abstract fun provideTwoFAFragment(): TwoFAFragment
}

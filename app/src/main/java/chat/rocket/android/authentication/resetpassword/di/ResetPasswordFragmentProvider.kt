package chat.dk.android.authentication.resetpassword.di

import chat.dk.android.authentication.resetpassword.ui.ResetPasswordFragment
import chat.dk.android.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ResetPasswordFragmentProvider {

    @ContributesAndroidInjector(modules = [ResetPasswordFragmentModule::class])
    @PerFragment
    abstract fun provideResetPasswordFragment(): ResetPasswordFragment
}
package chat.dk.android.webview.adminpanel.di

import chat.dk.android.webview.adminpanel.ui.AdminPanelWebViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AdminPanelWebViewFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideAdminPanelWebViewFragment(): AdminPanelWebViewFragment
}
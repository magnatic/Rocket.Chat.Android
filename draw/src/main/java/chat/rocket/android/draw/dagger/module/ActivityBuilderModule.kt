package chat.dk.android.draw.dagger.module

import chat.dk.android.draw.main.di.DrawModule
import chat.dk.android.draw.main.ui.DrawingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [DrawModule::class])
    abstract fun contributeDrawingActivityInjector(): DrawingActivity
}
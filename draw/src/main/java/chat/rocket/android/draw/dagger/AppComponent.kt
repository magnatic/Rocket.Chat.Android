package chat.dk.android.draw.dagger

import chat.dk.android.draw.dagger.module.ActivityBuilderModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerApplication>()
}
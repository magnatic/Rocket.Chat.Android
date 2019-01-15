package chat.dk.android.files.di

import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.files.ui.FilesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FilesFragmentProvider {

    @ContributesAndroidInjector(modules = [FilesFragmentModule::class])
    @PerFragment
    abstract fun provideFilesFragment(): FilesFragment
}
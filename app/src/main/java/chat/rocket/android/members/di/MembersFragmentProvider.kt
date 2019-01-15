package chat.dk.android.members.di

import chat.dk.android.members.ui.MembersFragment
import chat.dk.android.dagger.scope.PerFragment
import chat.dk.android.members.ui.MemberBottomSheetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MembersFragmentProvider {

    @ContributesAndroidInjector(modules = [MembersFragmentModule::class])
    @PerFragment
    abstract fun provideMembersFragment(): MembersFragment

    @ContributesAndroidInjector()
    @PerFragment
    abstract fun provideMemberBottomSheetFragment(): MemberBottomSheetFragment

}
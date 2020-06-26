package com.mywallet.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mywallet.common.ViewModelFactory
import com.mywallet.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindUserViewModel(userViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpenseCategoryViewModel::class)
    internal abstract fun bindExpenseCategoryViewModel(expenseCategoryViewModel: ExpenseCategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomeCategoryViewModel::class)
    internal abstract fun bindIncomeCategoryViewModel(incomeCategoryViewModel: IncomeCategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    internal abstract fun bindSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    internal abstract fun bindTransactionsViewModel(transactionsViewModel: TransactionViewModel): ViewModel

    /*@Binds
    @IntoMap
    @ViewModelKey(RepoViewModel::class)
    internal abstract fun bindRepoViewModel(repoViewModel: RepoViewModel): ViewModel*/

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
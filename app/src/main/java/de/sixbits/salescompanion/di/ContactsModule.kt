package de.sixbits.salescompanion.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import de.sixbits.salescompanion.di.ViewModelKey
import de.sixbits.salescompanion.view_model.MainViewModel

@Module
abstract class ContactsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel
}
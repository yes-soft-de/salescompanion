package de.sixbits.salescompanion.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import de.sixbits.salescompanion.view_model.main.DeviceContactsViewModel
import de.sixbits.salescompanion.view_model.main.MainViewModel
import de.sixbits.salescompanion.view_model.main.NetworkContactsViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeviceContactsViewModel::class)
    abstract fun bindsDeviceContactsViewModel(viewModel: DeviceContactsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NetworkContactsViewModel::class)
    abstract fun bindsNetworkContactsViewModel(viewModel: NetworkContactsViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
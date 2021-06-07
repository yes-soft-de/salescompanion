package de.sixbits.salescompanion.di

import dagger.Subcomponent
import de.sixbits.salescompanion.view.main.MainActivity
import de.sixbits.salescompanion.view.main.fragments.DeviceContactsListFragment
import de.sixbits.salescompanion.view.main.fragments.HubspotContactsListFragment

@Subcomponent(modules = [
    ContactsModule::class
])
interface ContactsComponent {
    @Subcomponent.Factory interface Factory {
        fun create(): ContactsComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: HubspotContactsListFragment)
    fun inject(fragment: DeviceContactsListFragment)
}
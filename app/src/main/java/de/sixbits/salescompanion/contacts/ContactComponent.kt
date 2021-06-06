package de.sixbits.salescompanion.contacts

import dagger.Subcomponent

@Subcomponent(
    modules = [
        ContactModule::class
    ]
)
interface ContactComponent {
    @Subcomponent.Factory interface Factory {
        fun create(): ContactComponent
    }
}
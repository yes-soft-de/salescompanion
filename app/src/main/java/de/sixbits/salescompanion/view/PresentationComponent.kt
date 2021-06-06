package de.sixbits.salescompanion.view

import dagger.Subcomponent
import de.sixbits.salescompanion.view.main.MainActivity

@Subcomponent(modules = [
    PresentationModule::class
])
interface PresentationComponent {
    @Subcomponent.Factory interface Factory {
        fun create(): PresentationComponent
    }

    fun inject(activity: MainActivity)
}
package de.sixbits.salescompanion

import android.app.Application
import de.sixbits.salescompanion.di.AppComponent
import de.sixbits.salescompanion.di.DaggerAppComponent

open class MyApplication : Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}
package de.sixbits.salescompanion.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import de.sixbits.salescompanion.MyApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

//    fun networkComponent(): NetworkComponent.Factory
//    fun mainComponent(): MainComponent.Factory
//    fun databaseComponent(): DatabaseComponent.Factory
}
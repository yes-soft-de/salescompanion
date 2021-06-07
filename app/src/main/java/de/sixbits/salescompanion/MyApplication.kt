package de.sixbits.salescompanion

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp(MultiDexApplication::class)
open class MyApplication : Hilt_MyApplication() {
}
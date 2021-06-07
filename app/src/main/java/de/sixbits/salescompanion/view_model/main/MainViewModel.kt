package de.sixbits.salescompanion.view_model.main

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(): ViewModel() {
    var isInNetwork = false

    fun setOnNetwork(onNetwork: Boolean) {
        isInNetwork = onNetwork
    }
}
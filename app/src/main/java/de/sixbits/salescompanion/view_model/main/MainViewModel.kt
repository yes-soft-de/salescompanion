package de.sixbits.salescompanion.view_model.main

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var isInNetwork = false

    fun setOnNetwork(onNetwork: Boolean) {
        isInNetwork = onNetwork
    }
}
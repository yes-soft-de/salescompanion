package de.sixbits.salescompanion.view_model.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    var activePage: ActivePage = ActivePage.DEVICE

    fun setOnNetwork(activePage: ActivePage) {
        this.activePage = activePage
    }
}

enum class ActivePage {
    NETWORK,
    DEVICE
}
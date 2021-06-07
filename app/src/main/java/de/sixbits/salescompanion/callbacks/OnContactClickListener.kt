package de.sixbits.salescompanion.callbacks

import de.sixbits.salescompanion.data_model.SalesContactDataModel

interface OnContactClickListener {
    fun onContactClick(contact: SalesContactDataModel)
}
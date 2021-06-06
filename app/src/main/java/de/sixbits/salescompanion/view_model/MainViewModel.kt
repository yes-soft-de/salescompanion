package de.sixbits.salescompanion.view_model

import androidx.lifecycle.ViewModel
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.service.ContactService
import javax.inject.Inject

class MainViewModel @Inject constructor(private val contactService: ContactService) : ViewModel() {

    var hubspotContacts = listOf<SalesContactDataModel>()
    var deviceContacts = listOf<SalesContactDataModel>()

    fun init() {
        contactService.getNetworkContacts().subscribe {
            hubspotContacts = it

            contactService.getDeviceContacts().subscribe { deviceContactList ->
                deviceContacts = deviceContactList.filter { deviceContactItem ->
                    hubspotContacts.contains(deviceContactItem)
                }
            }
        }
    }
}

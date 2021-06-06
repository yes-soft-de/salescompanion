package de.sixbits.salescompanion.view_model

import androidx.lifecycle.ViewModel
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.service.ContactService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MainViewModel @Inject constructor(private val contactService: ContactService) : ViewModel() {

    var contacts = mutableListOf<SalesContactDataModel>()

    fun init() {
        contactService.getNetworkContacts()
            .doOnNext {
                // First Add all the network contacts
                contacts.addAll(it)

                // Then Add only the contacts which are not included in the previous list
                contactService.getDeviceContacts()
                    .doOnNext { contactList ->
                        contactList.forEach { contactItem ->
                            if (!contacts.contains(contactItem)) {
                                contacts.add(contactItem)
                            }
                        }
                    }
            }.subscribe()
    }
}
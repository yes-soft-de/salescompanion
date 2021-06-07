package de.sixbits.salescompanion.view_model.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.sixbits.salescompanion.callbacks.OnContactClickListener
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.service.ContactService
import de.sixbits.salescompanion.view.main.recycler_view.ContactsRecyclerViewAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DeviceContactsViewModel @Inject constructor(private val contactService: ContactService) :
    ViewModel(), OnContactClickListener {
    val snacksLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()
    var contactsAdapter = ContactsRecyclerViewAdapter(listOf(), this)

    fun getDeviceContacts() {
        loadingLiveData.postValue(true)
        contactService.getNetworkContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { hubspotContacts ->
                contactService.getDeviceContacts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { deviceContacts ->
                        // First Build a context for search
                        val index = buildIndex(hubspotContacts)

                        // Then remove all the synced contacts
                        // Then Post the result
                        contactsAdapter.replaceContacts(deviceContacts.filter {
                            index.contains("${it.firstName} ${it.lastName}".replace(" ", ""))
                        })
                        loadingLiveData.postValue(false)
                    }
            }
    }

    private fun buildIndex(hubspotContacts: List<SalesContactDataModel>): List<String> {
        val synced = mutableListOf<String>()
        hubspotContacts.forEach {
            synced.add("${it.firstName} ${it.lastName}".replace(" ", ""))
        }
        return synced
    }

    override fun onContactClick(contact: SalesContactDataModel) {
        contactService.createHubSpotContact(contact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                snacksLiveData.postValue("Saving Contact!")
                getDeviceContacts()
            }, {
                snacksLiveData.postValue("Error Submitting Data $it")
            })
    }

}
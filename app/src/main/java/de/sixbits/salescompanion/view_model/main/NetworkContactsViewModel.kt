package de.sixbits.salescompanion.view_model.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.sixbits.salescompanion.callbacks.OnContactClickListener
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.service.ContactService
import de.sixbits.salescompanion.view.main.recycler_view.ContactsRecyclerViewAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class NetworkContactsViewModel @Inject constructor(private val contactService: ContactService) :
    ViewModel(),
    OnContactClickListener {
    // Data Holders
    val contactsAdapter = ContactsRecyclerViewAdapter(listOf(), this)

    // Refreshers
    val snacksLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getNetworkContacts() {
        loadingLiveData.postValue(true)
        contactService.getNetworkContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                contactsAdapter.replaceContacts(it.map { contact ->
                    SalesContactDataModel(
                        firstName = contact.firstName,
                        lastName = contact.lastName,
                        email = contact.email,
                        phone = contact.phone,
                        company = contact.company,
                        createdAt = contact.createdAt,
                        updatedAt = contact.updatedAt,
                        synced = true,
                    )
                })
                loadingLiveData.postValue(false)
            }, {
                snacksLiveData.postValue("Error Getting Network Contacts $it")
            })
    }

    override fun onContactClick(contact: SalesContactDataModel) {
        contactService.createHubSpotContact(contact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                snacksLiveData.postValue("Saving Contact!")
                getNetworkContacts()
            }, {
                snacksLiveData.postValue("Error Submitting Data $it")
            })
    }
}

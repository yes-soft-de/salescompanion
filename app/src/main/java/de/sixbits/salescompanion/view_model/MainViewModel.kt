package de.sixbits.salescompanion.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.sixbits.salescompanion.callbacks.OnContactClickListener
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.service.ContactService
import de.sixbits.salescompanion.view.main.recycler_view.ContactsRecyclerViewAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

private const val TAG = "MainViewModel"

class MainViewModel @Inject constructor(private val contactService: ContactService) : ViewModel(),
    OnContactClickListener {
    // Data Holders
    private var hubspotContacts = listOf<SalesContactDataModel>()
    private var deviceContacts = listOf<SalesContactDataModel>()

    // Refreshers
    val snacksLiveData = MutableLiveData<String>()
    val contactsAdapterLiveData = MutableLiveData<ContactsRecyclerViewAdapter>()
    val loadingLiveData = MutableLiveData<Boolean>()

    // What to show from the data
    private var contactsToShow: CONTACTS_VISIBLE = CONTACTS_VISIBLE.ALL

    fun setVisible(contactsVisible: CONTACTS_VISIBLE) {
        contactsToShow = contactsVisible
    }

    private fun refreshViews() {
        when (contactsToShow) {
            CONTACTS_VISIBLE.ALL -> {
                contactsAdapterLiveData.postValue(
                    ContactsRecyclerViewAdapter(hubspotContacts + deviceContacts, this)
                )
            }
            CONTACTS_VISIBLE.DEVICE -> {
                contactsAdapterLiveData.postValue(
                    ContactsRecyclerViewAdapter(deviceContacts, this)
                )
            }
            else -> {
                contactsAdapterLiveData.postValue(
                    ContactsRecyclerViewAdapter(hubspotContacts, this)
                )
            }
        }
        loadingLiveData.postValue(false)
    }

    fun getNetworkContacts() {
        loadingLiveData.postValue(true)
        contactService.getNetworkContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                hubspotContacts = it
                refreshViews()
            }, {
                snacksLiveData.postValue("Error Getting Network Contacts $it")
            })
    }

    fun getDeviceContacts() {
        loadingLiveData.postValue(true)
        contactService.getDeviceContacts().subscribe { deviceContactList ->
            deviceContacts = deviceContactList.filter { deviceContactItem ->
                !hubspotContacts.contains(deviceContactItem)
            }

            refreshViews()
        }
    }

    override fun onContactClick(contact: SalesContactDataModel) {
        snacksLiveData.postValue("${contact.firstName} Clicked!")
    }

    enum class CONTACTS_VISIBLE {
        ALL,
        HUBSPOT,
        DEVICE
    }
}

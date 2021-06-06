package de.sixbits.salescompanion.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.service.ContactService
import de.sixbits.salescompanion.view.main.recycler_view.ContactsRecyclerViewAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

private const val TAG = "MainViewModel"

class MainViewModel @Inject constructor(private val contactService: ContactService) : ViewModel() {
    var hubspotContacts = listOf<SalesContactDataModel>()
    var deviceContacts = listOf<SalesContactDataModel>()

    fun bindDeviceContactsRecyclerView(rv: RecyclerView) {
        contactService.getNetworkContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "init: Got Network Contacts")
                hubspotContacts = it

                contactService.getDeviceContacts().subscribe { deviceContactList ->
                    Log.d(TAG, "init: Got Device Contacts")
                    deviceContacts = deviceContactList.filter { deviceContactItem ->
                        !hubspotContacts.contains(deviceContactItem)
                    }

                    Log.d(TAG, "Attaching Adapter! with ${deviceContacts.size} contacts")
                    rv.adapter = ContactsRecyclerViewAdapter(deviceContacts)
                }
            }, {
                Log.d(TAG, "init: Error: $it")
            })
    }
}

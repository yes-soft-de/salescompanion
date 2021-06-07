package de.sixbits.salescompanion.view.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import de.sixbits.salescompanion.MyApplication
import de.sixbits.salescompanion.R
import de.sixbits.salescompanion.databinding.ActivityMainBinding
import de.sixbits.salescompanion.databinding.FragmentDeviceContactsListBinding
import de.sixbits.salescompanion.di.ContactsComponent
import de.sixbits.salescompanion.view.main.recycler_view.ContactsRecyclerViewAdapter
import de.sixbits.salescompanion.view_model.main.DeviceContactsViewModel
import de.sixbits.salescompanion.view_model.main.NetworkContactsViewModel
import javax.inject.Inject

class DeviceContactsListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mainComponent: ContactsComponent
    lateinit var uiBindings: FragmentDeviceContactsListBinding

    lateinit var deviceContactsViewModel: DeviceContactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (activity?.application as MyApplication)
            .appComponent
            .presentationComponent()
            .create()
        mainComponent.inject(this)

        super.onCreate(savedInstanceState)

        deviceContactsViewModel = ViewModelProvider(this, viewModelFactory)
            .get(DeviceContactsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView =
            inflater.inflate(R.layout.fragment_device_contacts_list, container, false)

        uiBindings = FragmentDeviceContactsListBinding.inflate(layoutInflater)
        initViews()
        setupListener()

        return fragmentView
    }

    private fun setupListener() {
        deviceContactsViewModel.loadingLiveData.observe(viewLifecycleOwner, { loading ->
            if (loading) {
                uiBindings.rvContactList.visibility = View.GONE
                uiBindings.pbContactsLoading.visibility = View.VISIBLE
            } else {
                uiBindings.rvContactList.visibility = View.VISIBLE
                uiBindings.pbContactsLoading.visibility = View.GONE
            }
        })

        deviceContactsViewModel.snacksLiveData.observe(viewLifecycleOwner, {
            Snackbar.make(uiBindings.root, it, Snackbar.LENGTH_SHORT).show()
        })
    }

    private fun initViews() {
        uiBindings.rvContactList.layoutManager = LinearLayoutManager(context)
        uiBindings.rvContactList.adapter = deviceContactsViewModel.contactsAdapter
        deviceContactsViewModel.getDeviceContacts()
    }
}
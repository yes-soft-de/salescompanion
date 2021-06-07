package de.sixbits.salescompanion.view.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.sixbits.salescompanion.MyApplication
import de.sixbits.salescompanion.R
import de.sixbits.salescompanion.databinding.FragmentDeviceContactsListBinding
import de.sixbits.salescompanion.di.ContactsComponent
import de.sixbits.salescompanion.view_model.main.DeviceContactsViewModel
import de.sixbits.salescompanion.view_model.main.NetworkContactsViewModel
import javax.inject.Inject


class HubspotContactsListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mainComponent: ContactsComponent
    lateinit var uiBindings: FragmentDeviceContactsListBinding

    lateinit var hubContactsViewModel: NetworkContactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (activity?.application as MyApplication)
            .appComponent
            .presentationComponent()
            .create()

        mainComponent.inject(this)

        super.onCreate(savedInstanceState)

        hubContactsViewModel = ViewModelProvider(this, viewModelFactory)
            .get(NetworkContactsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =
            inflater.inflate(R.layout.fragment_hubspot_contacts_list, container, false)

        setupViews()
        setupListener()

        return fragmentView
    }

    private fun setupViews() {
        uiBindings = FragmentDeviceContactsListBinding.inflate(layoutInflater)
        uiBindings.rvContactList.layoutManager = LinearLayoutManager(context)
        uiBindings.rvContactList.adapter = hubContactsViewModel.contactsAdapter

        hubContactsViewModel.getNetworkContacts()
    }

    private fun setupListener() {
        hubContactsViewModel.loadingLiveData.observe(viewLifecycleOwner, { loading ->
            if (loading) {
                uiBindings.rvContactList.visibility = View.GONE
                uiBindings.pbContactsLoading.visibility = View.VISIBLE
            } else {
                uiBindings.rvContactList.visibility = View.VISIBLE
                uiBindings.pbContactsLoading.visibility = View.GONE
            }
        })
    }
}
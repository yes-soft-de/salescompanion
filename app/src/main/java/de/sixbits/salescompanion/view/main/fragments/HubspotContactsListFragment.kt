package de.sixbits.salescompanion.view.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import de.sixbits.salescompanion.R
import de.sixbits.salescompanion.databinding.FragmentDeviceContactsListBinding
import de.sixbits.salescompanion.view_model.main.NetworkContactsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HubspotContactsListFragment : Fragment() {

    lateinit var uiBindings: FragmentDeviceContactsListBinding

    private val hubContactsViewModel: NetworkContactsViewModel by viewModels()

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
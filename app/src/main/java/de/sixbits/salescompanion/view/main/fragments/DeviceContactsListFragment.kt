package de.sixbits.salescompanion.view.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import de.sixbits.salescompanion.MyApplication
import de.sixbits.salescompanion.R
import de.sixbits.salescompanion.databinding.FragmentDeviceContactsListBinding
import de.sixbits.salescompanion.view_model.main.DeviceContactsViewModel
import javax.inject.Inject

private const val TAG = "DeviceContactsListFragm"

@AndroidEntryPoint
class DeviceContactsListFragment : Fragment() {
    private val deviceContactsViewModel: DeviceContactsViewModel by viewModels()

    lateinit var uiBindings: FragmentDeviceContactsListBinding

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

        uiBindings.srContactList.setOnRefreshListener {
            deviceContactsViewModel.getDeviceContacts()
        }
    }

    private fun initViews() {
        uiBindings.rvContactList.layoutManager = LinearLayoutManager(context)
        uiBindings.rvContactList.adapter = deviceContactsViewModel.contactsAdapter
        deviceContactsViewModel.getDeviceContacts()
    }
}
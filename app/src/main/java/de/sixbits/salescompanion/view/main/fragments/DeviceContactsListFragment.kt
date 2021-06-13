package de.sixbits.salescompanion.view.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import de.sixbits.salescompanion.databinding.FragmentDeviceContactsListBinding
import de.sixbits.salescompanion.view_model.main.DeviceContactsViewModel

private const val TAG = "DeviceContactsListFragm"

@AndroidEntryPoint
class DeviceContactsListFragment : Fragment() {
    private val deviceContactsViewModel: DeviceContactsViewModel by viewModels()

    lateinit var uiBindings: FragmentDeviceContactsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        uiBindings = FragmentDeviceContactsListBinding.inflate(
            inflater,
            container,
            false
        )

        uiBindings = FragmentDeviceContactsListBinding.inflate(layoutInflater)
        initViews()
        setupListener()

        return uiBindings.root
    }

    private fun setupListener() {
        deviceContactsViewModel.loadingLiveData.observe(viewLifecycleOwner, { loading ->
            uiBindings.srContactList.isRefreshing = loading
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
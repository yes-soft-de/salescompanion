package de.sixbits.salescompanion.view.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import de.sixbits.salescompanion.MyApplication
import de.sixbits.salescompanion.databinding.ActivityMainBinding
import de.sixbits.salescompanion.di.PresentationComponent
import de.sixbits.salescompanion.view_model.MainViewModel
import javax.inject.Inject


const val REQUEST_CONTACTS_PERMISSIONS_CODE = 100

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var presentationComponent: PresentationComponent
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        presentationComponent = (application as MyApplication)
            .appComponent
            .presentationComponent()
            .create()
        presentationComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        // Check for Permissions
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                listOf(Manifest.permission.ACCESS_FINE_LOCATION).toTypedArray(),
                REQUEST_CONTACTS_PERMISSIONS_CODE
            )
            return
        }

        binding.rvContactList.layoutManager = LinearLayoutManager(baseContext)
        mainViewModel.getNetworkContacts()
        mainViewModel.getDeviceContacts()
    }

    private fun setupListeners() {
        mainViewModel.contactsAdapterLiveData.observe(this, {
            binding.rvContactList.adapter = it
        })
        mainViewModel.snacksLiveData.observe(this, {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })
        mainViewModel.loadingLiveData.observe(this, { loading ->
            if (loading) {
                binding.pbContactsLoading.visibility = View.VISIBLE
                binding.rvContactList.visibility = View.GONE
            } else {
                binding.pbContactsLoading.visibility = View.GONE
                binding.rvContactList.visibility = View.VISIBLE
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CONTACTS_PERMISSIONS_CODE) {
            initViews()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

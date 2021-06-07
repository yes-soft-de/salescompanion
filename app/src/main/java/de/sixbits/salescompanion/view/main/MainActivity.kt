package de.sixbits.salescompanion.view.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import de.sixbits.salescompanion.MyApplication
import de.sixbits.salescompanion.R
import de.sixbits.salescompanion.databinding.ActivityMainBinding
import de.sixbits.salescompanion.di.ContactsComponent
import de.sixbits.salescompanion.view.main.fragments.DeviceContactsListFragment
import de.sixbits.salescompanion.view_model.main.MainViewModel
import de.sixbits.salescompanion.view_model.main.NetworkContactsViewModel
import javax.inject.Inject


const val REQUEST_CONTACTS_PERMISSIONS_CODE = 100

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var contactsComponent: ContactsComponent
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        contactsComponent = (application as MyApplication)
            .appComponent
            .presentationComponent()
            .create()
        contactsComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        if (mainViewModel.isInNetwork) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main_fragment_container, DeviceContactsListFragment())
                .commit()
        }
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
    }

    private fun setupListeners() {

    }
}

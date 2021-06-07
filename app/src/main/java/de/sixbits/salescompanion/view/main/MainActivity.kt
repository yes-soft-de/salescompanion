package de.sixbits.salescompanion.view.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import de.sixbits.salescompanion.R
import de.sixbits.salescompanion.databinding.ActivityMainBinding
import de.sixbits.salescompanion.view.main.fragments.DeviceContactsListFragment
import de.sixbits.salescompanion.view_model.main.MainViewModel
import javax.inject.Inject


const val REQUEST_CONTACTS_PERMISSIONS_CODE = 100

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

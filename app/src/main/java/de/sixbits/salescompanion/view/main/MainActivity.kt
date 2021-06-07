package de.sixbits.salescompanion.view.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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


        checkPermissions()
    }

    private fun initViews() {
        binding.rvContactList.layoutManager = LinearLayoutManager(baseContext)
        mainViewModel.bindDeviceContactsRecyclerView(binding.rvContactList)
    }

    private fun checkPermissions() {
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
        } else {
            initViews()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CONTACTS_PERMISSIONS_CODE) {
            checkPermissions()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun requestPermissions() {
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                initViews()
            }
        }
    }
}

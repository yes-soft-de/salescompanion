package de.sixbits.salescompanion.view.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import de.sixbits.salescompanion.R
import de.sixbits.salescompanion.databinding.ActivityMainBinding
import de.sixbits.salescompanion.view.main.fragments.DeviceContactsListFragment
import de.sixbits.salescompanion.view.main.fragments.HubspotContactsListFragment

const val REQUEST_CONTACTS_PERMISSIONS_CODE = 100

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        switchToDeviceContacts()
    }

    private fun setupListeners() {
        binding.tlListType.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_main_fragment_container, DeviceContactsListFragment())
                        .commit()
                } else {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_main_fragment_container, HubspotContactsListFragment())
                        .commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun switchToDeviceContacts() {
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
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main_fragment_container, DeviceContactsListFragment())
                .commit()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CONTACTS_PERMISSIONS_CODE) {
            switchToDeviceContacts()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

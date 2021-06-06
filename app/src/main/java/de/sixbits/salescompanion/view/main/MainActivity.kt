package de.sixbits.salescompanion.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import de.sixbits.salescompanion.MyApplication
import de.sixbits.salescompanion.databinding.ActivityMainBinding
import de.sixbits.salescompanion.view.PresentationComponent
import de.sixbits.salescompanion.view_model.MainViewModel
import javax.inject.Inject

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
    }
}

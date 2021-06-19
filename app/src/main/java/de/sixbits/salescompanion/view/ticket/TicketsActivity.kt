package de.sixbits.salescompanion.view.ticket

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.common.io.CharStreams
import dagger.hilt.android.AndroidEntryPoint
import de.sixbits.salescompanion.databinding.ActivityTicketsBinding
import de.sixbits.salescompanion.view_model.ticket.TicketViewModel
import java.io.InputStreamReader
import androidx.databinding.DataBindingUtil
import de.sixbits.salescompanion.R

private const val TAG = "TicketsActivity"

@AndroidEntryPoint
class TicketsActivity : AppCompatActivity() {
    private lateinit var uiBinding: ActivityTicketsBinding
    private val ticketViewModel: TicketViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        uiBinding = DataBindingUtil.setContentView(this, R.layout.activity_tickets)
        uiBinding.lifecycleOwner = this
        uiBinding.ticketViewModel = ticketViewModel

        initListeners()

        when (intent?.action) {
            Intent.ACTION_SEND_MULTIPLE -> {
                handleSendText(intent)
            }
        }
    }

    fun initListeners() {
        ticketViewModel.snackBarLiveData.observe(this, {
            Snackbar.make(uiBinding.root, it, Snackbar.LENGTH_SHORT).show()
        })
    }

    @SuppressLint("SetTextI18n")
    private fun handleSendText(intent: Intent) {
        intent.getParcelableArrayListExtra<Parcelable>(Intent.EXTRA_STREAM)?.let {
            it.forEach { msg ->
                val textStream = baseContext.contentResolver.openInputStream(msg as Uri)
                val content = CharStreams.toString(InputStreamReader(textStream, Charsets.UTF_8))
                ticketViewModel.saveNewLog(content)
            }
        }
    }
}

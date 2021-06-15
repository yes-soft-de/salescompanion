package de.sixbits.salescompanion.view.ticket

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.google.common.io.CharStreams
import dagger.hilt.android.AndroidEntryPoint
import de.sixbits.salescompanion.databinding.ActivityTicketsBinding
import java.io.InputStreamReader

private const val TAG = "TicketsActivity"

@AndroidEntryPoint
class TicketsActivity : AppCompatActivity() {
    private lateinit var uiBinding: ActivityTicketsBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiBinding = ActivityTicketsBinding.inflate(layoutInflater)
        setContentView(uiBinding.root)

        when (intent?.action) {
            Intent.ACTION_SEND_MULTIPLE -> {
                handleSendText(intent)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleSendText(intent: Intent) {
        intent.getParcelableArrayListExtra<Parcelable>(Intent.EXTRA_STREAM)?.let {
            it.forEach { msg ->
                val textStream = baseContext.contentResolver.openInputStream(msg as Uri)
                val content = CharStreams.toString(InputStreamReader(textStream, Charsets.UTF_8))

                val messages = content.split("\n")

                messages.forEach { oneMessage ->
                    Log.d(TAG, "handleSendText: $oneMessage")
                }
                uiBinding.textView.text = "Got First Word: ${content.substring(0, 10)}"
            }
        }
    }
}

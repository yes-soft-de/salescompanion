package de.sixbits.salescompanion.view_model.ticket

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.sixbits.salescompanion.mapper.ChatMessagesMapper
import de.sixbits.salescompanion.service.ChatLogService
import de.sixbits.salescompanion.service.ContactService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

private const val TAG = "TicketViewModel"

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val contactService: ContactService,
    private val chatLogService: ChatLogService
) : ViewModel() {

    val snackBarLiveData = MutableLiveData<String>()
    val statusLiveDate = MutableLiveData<String>()

    fun saveNewLog(chatLog: String) {
        Log.d(TAG, "saveNewLog: Saving New Log")

        // First map chat logs
        val messages = ChatMessagesMapper.toChatMessagesDateModelList(chatLog)
        Log.d(TAG, "saveNewLog: Messages Size ${messages.size}")

        // Next extract names from messages
        val names = mutableListOf<String>()
        for (id in messages) {
            if (names.size >= 2) {
                break
            }
            names.add(id.name)
        }

        // Next Get the ids from these messages
        names.forEach { name ->
            contactService.searchContact(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG, "saveNewLog: Got Contact Id $it for $name")
                    statusLiveDate.postValue("Got Contact Id $it")

                    if (it.isNotEmpty()) {
                        // Now post the chat
                        chatLogService.createChatLog(chatLog = messages, contactId = it)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ emailCreateResponse ->
                                Log.d(
                                    TAG,
                                    "saveNewLog: Saved chat log ${emailCreateResponse.engagement.id}"
                                )
                                statusLiveDate.postValue("Saved chat log ${emailCreateResponse.engagement.id}")
                            }, { err ->
                                Log.d(TAG, "saveNewLog: Error! $err")
                                snackBarLiveData.postValue("Error! $err")
                            })
                    }
                }, {
                    Log.d(TAG, "saveNewLog: $it")
                    snackBarLiveData.postValue("Error: $it")
                })
        }
    }
}
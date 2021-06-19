package de.sixbits.salescompanion.service

import de.sixbits.salescompanion.data_model.ChatMessageDataModel
import de.sixbits.salescompanion.mapper.ChatLogMapper
import de.sixbits.salescompanion.network.HubspotApi
import de.sixbits.salescompanion.response.CreateEmailLogResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject


class ChatLogService @Inject constructor(
    private val hubspotApi: HubspotApi,
    private val contactService: ContactService
) {
    fun createChatLog(
        chatLog: List<ChatMessageDataModel>,
        contactId: String
    ): Observable<CreateEmailLogResponse> {
        return hubspotApi.createLog(
            ChatLogMapper.toCreateEmailLogRequest(
                chatLog = chatLog,
                contactId = contactId
            )
        )
    }
}
package de.sixbits.salescompanion.service

import de.sixbits.salescompanion.data_model.ChatMessageDataModel
import de.sixbits.salescompanion.mapper.ChatLogMapper
import de.sixbits.salescompanion.network.HubspotApi
import de.sixbits.salescompanion.response.CreateEmailLogResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class ChatLogService @Inject constructor(private val hubspotApi: HubspotApi) {
    fun createChatLog(chatLog: List<ChatMessageDataModel>): Observable<CreateEmailLogResponse> {
        return hubspotApi.createLog(
            createEmailLogRequest = ChatLogMapper.toCreateEmailLogResponse(chatLog)
        )
    }
}
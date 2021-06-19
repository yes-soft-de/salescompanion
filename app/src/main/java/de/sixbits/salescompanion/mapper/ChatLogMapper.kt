package de.sixbits.salescompanion.mapper

import de.sixbits.salescompanion.data_model.ChatMessageDataModel
import de.sixbits.salescompanion.request.CreateEmailLogRequest
import de.sixbits.salescompanion.templates.EmailTemplates

object ChatLogMapper {
    fun toCreateEmailLogRequest(
        chatLog: List<ChatMessageDataModel>,
        contactId: String
    ): CreateEmailLogRequest {
        return CreateEmailLogRequest(
            engagement = CreateEmailLogRequest.Engagement(
                active = true,
                ownerId = 1,
                type = "Email"
            ),
            associations = CreateEmailLogRequest.Associations(
                contactIds = listOf(contactId.toInt()),
                companyIds = listOf(),
                dealIds = listOf(),
                ticketIds = listOf(),
                ownerIds = listOf()
            ),
            metadata = CreateEmailLogRequest.Metadata(
                bcc = listOf(),
                cc = listOf(),
                from = CreateEmailLogRequest.Metadata.From(
                    email = " ",
                    firstName = " ",
                    lastName = " "
                ),
                to = listOf(),
                html = EmailTemplates.getHtmlEmailLog(chatLog, chatLog[0].name),
                subject = "Chat Log for Date: ${chatLog[0].date}"
            )
        )
    }
}
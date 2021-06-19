package de.sixbits.salescompanion.response


import com.squareup.moshi.Json

data class CreateEmailLogResponse(
    @Json(name = "associationCreateFailures")
    val associationCreateFailures: List<Any?>?,
    @Json(name = "associations")
    val associations: Associations?,
    @Json(name = "attachments")
    val attachments: List<Any?>?,
    @Json(name = "engagement")
    val engagement: Engagement?,
    @Json(name = "metadata")
    val metadata: Metadata?
) {
    data class Associations(
        @Json(name = "companyIds")
        val companyIds: List<Any?>?,
        @Json(name = "contactIds")
        val contactIds: List<Int?>?,
        @Json(name = "contentIds")
        val contentIds: List<Any?>?,
        @Json(name = "dealIds")
        val dealIds: List<Any?>?,
        @Json(name = "ownerIds")
        val ownerIds: List<Any?>?,
        @Json(name = "quoteIds")
        val quoteIds: List<Any?>?,
        @Json(name = "ticketIds")
        val ticketIds: List<Any?>?,
        @Json(name = "workflowIds")
        val workflowIds: List<Any?>?
    )

    data class Engagement(
        @Json(name = "active")
        val active: Boolean?, // true
        @Json(name = "allAccessibleTeamIds")
        val allAccessibleTeamIds: List<Any?>?,
        @Json(name = "bodyPreview")
        val bodyPreview: String?, // Chat History between Mohammad and Ali Hello Hi
        @Json(name = "bodyPreviewHtml")
        val bodyPreviewHtml: String?, // <html> <head></head> <body> <div class="message-header"> Chat History between Mohammad and Ali  </div>  <div class="message-list">  <div class="our-message"> Hello  </div>  <div class="their-message"> Hi  </div>  </div> </body></html>
        @Json(name = "bodyPreviewIsTruncated")
        val bodyPreviewIsTruncated: Boolean?, // false
        @Json(name = "createdAt")
        val createdAt: Long?, // 1624090791563
        @Json(name = "gdprDeleted")
        val gdprDeleted: Boolean?, // false
        @Json(name = "id")
        val id: Long?, // 13944884779
        @Json(name = "lastUpdated")
        val lastUpdated: Long?, // 1624090791563
        @Json(name = "ownerId")
        val ownerId: Int?, // 1
        @Json(name = "portalId")
        val portalId: Int?, // 20191517
        @Json(name = "queueMembershipIds")
        val queueMembershipIds: List<Any?>?,
        @Json(name = "timestamp")
        val timestamp: Long?, // 1624090791563
        @Json(name = "type")
        val type: String? // EMAIL
    )

    data class Metadata(
        @Json(name = "attachedVideoOpened")
        val attachedVideoOpened: Boolean?, // false
        @Json(name = "attachedVideoWatched")
        val attachedVideoWatched: Boolean?, // false
        @Json(name = "bcc")
        val bcc: List<Any?>?,
        @Json(name = "cc")
        val cc: List<Any?>?,
        @Json(name = "emailSendEventId")
        val emailSendEventId: EmailSendEventId?,
        @Json(name = "from")
        val from: From?,
        @Json(name = "html")
        val html: String?, // <!DOCTYPE html><html lang="en"><head> <meta charset="UTF-8"> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <title>Message List</title> <link rel="preconnect" href="https://fonts.gstatic.com"> <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet"> <style>body{padding: 0; margin: 0; font-family: 'Poppins', sans-serif;}.message-header{display: flex; padding: 1rem; background-color: aquamarine; margin-bottom: 2rem;}.message-list{display: flex; flex-direction: column; align-items: center; justify-content: center;}.our-message{text-align: end; background-color: blue; color: white; padding: .5rem; margin-bottom: .5rem; border-radius: 8px; align-self: flex-start; margin-left: 1rem;}.their-message{text-align: start; background-color: green; color: white; padding: .5rem; margin-bottom: .5rem; border-radius: 8px; align-self: flex-end; margin-right: 1rem;}</style></head><body> <div class="message-header"> Chat History between Mohammad and Ali </div><div class="message-list"> <div class="our-message">Hello</div><div class="their-message">Hi</div></div></body></html>
        @Json(name = "pendingInlineImageIds")
        val pendingInlineImageIds: List<Any?>?,
        @Json(name = "sender")
        val sender: Sender?,
        @Json(name = "subject")
        val subject: String?, // Contact Archive
        @Json(name = "to")
        val to: List<To?>?,
        @Json(name = "validationSkipped")
        val validationSkipped: List<Any?>?
    ) {
        class EmailSendEventId(
        )

        data class From(
            @Json(name = "email")
            val email: String?, // email@domain.com
            @Json(name = "firstName")
            val firstName: String?, // First
            @Json(name = "lastName")
            val lastName: String? // Last
        )

        class Sender(
        )

        data class To(
            @Json(name = "email")
            val email: String? // contact name <test@test.com>
        )
    }
}
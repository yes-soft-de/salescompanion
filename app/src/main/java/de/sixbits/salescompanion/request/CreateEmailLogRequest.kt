package de.sixbits.salescompanion.request


import com.squareup.moshi.Json

data class CreateEmailLogRequest(
    @Json(name = "associations")
    val associations: Associations?,
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
        @Json(name = "dealIds")
        val dealIds: List<Any?>?,
        @Json(name = "ownerIds")
        val ownerIds: List<Any?>?,
        @Json(name = "ticketIds")
        val ticketIds: List<Any?>?
    )

    data class Engagement(
        @Json(name = "active")
        val active: Boolean?, // true
        @Json(name = "ownerId")
        val ownerId: Int?, // 1
        @Json(name = "type")
        val type: String? // EMAIL
    )

    data class Metadata(
        @Json(name = "bcc")
        val bcc: List<Any?>?,
        @Json(name = "cc")
        val cc: List<Any?>?,
        @Json(name = "from")
        val from: From?,
        @Json(name = "html")
        val html: String?, // <!DOCTYPE html><html lang="en"><head> <meta charset="UTF-8"> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <title>Message List</title> <link rel="preconnect" href="https://fonts.gstatic.com"> <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet"> <style>body{padding: 0; margin: 0; font-family: 'Poppins', sans-serif;}.message-header{display: flex; padding: 1rem; background-color: aquamarine; margin-bottom: 2rem;}.message-list{display: flex; flex-direction: column; align-items: center; justify-content: center;}.our-message{text-align: end; background-color: blue; color: white; padding: .5rem; margin-bottom: .5rem; border-radius: 8px; align-self: flex-start; margin-left: 1rem;}.their-message{text-align: start; background-color: green; color: white; padding: .5rem; margin-bottom: .5rem; border-radius: 8px; align-self: flex-end; margin-right: 1rem;}</style></head><body> <div class="message-header"> Chat History between Mohammad and Ali </div><div class="message-list"> <div class="our-message">Hello</div><div class="their-message">Hi</div></div></body></html>
        @Json(name = "subject")
        val subject: String?, // Contact Archive
        @Json(name = "to")
        val to: List<To?>?
    ) {
        data class From(
            @Json(name = "email")
            val email: String?, // email@domain.com
            @Json(name = "firstName")
            val firstName: String?, // First
            @Json(name = "lastName")
            val lastName: String? // Last
        )

        data class To(
            @Json(name = "email")
            val email: String? // contact name <test@test.com>
        )
    }
}
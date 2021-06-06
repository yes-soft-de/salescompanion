package de.sixbits.salescompanion.response


import com.squareup.moshi.Json

data class HubSpotContactResponse(
    @Json(name = "archived")
    val archived: Boolean?, // false
    @Json(name = "createdAt")
    val createdAt: String?, // 2019-10-30T03:30:17.883Z
    @Json(name = "id")
    val id: String?, // 512
    @Json(name = "properties")
    val properties: Properties?,
    @Json(name = "updatedAt")
    val updatedAt: String? // 2019-12-07T16:50:06.678Z
) {
    data class Properties(
        @Json(name = "company")
        val company: String?, // Biglytics
        @Json(name = "createdate")
        val createdate: String?, // 2019-10-30T03:30:17.883Z
        @Json(name = "email")
        val email: String?, // bcooper@biglytics.net
        @Json(name = "firstname")
        val firstname: String?, // Bryan
        @Json(name = "lastmodifieddate")
        val lastmodifieddate: String?, // 2019-12-07T16:50:06.678Z
        @Json(name = "lastname")
        val lastname: String?, // Cooper
        @Json(name = "phone")
        val phone: String?, // (877) 929-0687
        @Json(name = "website")
        val website: String? // biglytics.net
    )
}
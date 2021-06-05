package de.sixbits.salescompanion.request


import com.squareup.moshi.Json

data class CreateHubSpotContactRequest(
    @Json(name = "properties")
    val properties: Properties
) {
    data class Properties(
        @Json(name = "company")
        val company: String, // Biglytics
        @Json(name = "email")
        val email: String, // bcooper@biglytics.net
        @Json(name = "firstname")
        val firstname: String, // Bryan
        @Json(name = "lastname")
        val lastname: String, // Cooper
        @Json(name = "phone")
        val phone: String, // (877) 929-0687
        @Json(name = "website")
        val website: String // biglytics.net
    )
}
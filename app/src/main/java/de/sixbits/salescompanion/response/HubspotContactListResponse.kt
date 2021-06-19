package de.sixbits.salescompanion.response

import com.squareup.moshi.Json

data class HubspotContactListResponse(val results: List<Result>) {
    data class Result(
        @Json(name = "properties")
        val properties: Properties,
        @Json(name = "id")
        val id: String
    ) {
        data class Properties(
            @Json(name = "firstname")
            val firstname: String,
            @Json(name = "lastname")
            val lastname: String,
            @Json(name = "createdate")
            val createdate: String,
            @Json(name = "lastmodifieddate")
            val lastmodifieddate: String,
            @Json(name = "phone")
            val phone: String,
        )
    }
}
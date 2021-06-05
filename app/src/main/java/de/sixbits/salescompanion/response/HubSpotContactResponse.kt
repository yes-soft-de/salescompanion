package de.sixbits.salescompanion.response


import com.squareup.moshi.Json

data class HubSpotContactResponse(
    @Json(name = "paging")
    val paging: Paging,
    @Json(name = "results")
    val results: List<Result>
) {
    data class Paging(
        @Json(name = "next")
        val next: Next
    ) {
        data class Next(
            @Json(name = "after")
            val after: String, // NTI1Cg%3D%3D
            @Json(name = "link")
            val link: String // ?after=NTI1Cg%3D%3D
        )
    }

    data class Result(
        @Json(name = "properties")
        val properties: Properties
    ) {
        data class Properties(
            @Json(name = "company")
            val company: String, // Biglytics
            @Json(name = "createdate")
            val createdate: String, // 2019-10-30T03:30:17.883Z
            @Json(name = "email")
            val email: String, // bcooper@biglytics.net
            @Json(name = "firstname")
            val firstname: String, // Bryan
            @Json(name = "lastmodifieddate")
            val lastmodifieddate: String, // 2019-12-07T16:50:06.678Z
            @Json(name = "lastname")
            val lastname: String, // Cooper
            @Json(name = "phone")
            val phone: String, // (877) 929-0687
            @Json(name = "website")
            val website: String // biglytics.net
        )
    }
}
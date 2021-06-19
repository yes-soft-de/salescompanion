package de.sixbits.salescompanion.response


import com.squareup.moshi.Json

data class CreateEmailLogResponse(
    @Json(name = "engagement") val engagement: Engagement
) {
    data class Engagement(
        @Json(name = "id") val id: Long?
    )
}
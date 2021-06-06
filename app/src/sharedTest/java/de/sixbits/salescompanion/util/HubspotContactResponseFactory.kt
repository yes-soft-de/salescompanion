package de.sixbits.salescompanion.util

import de.sixbits.salescompanion.response.HubSpotContactResponse
import java.util.*

object HubspotContactResponseFactory {
    fun getResponseItem(): HubSpotContactResponse.Result {
        return HubSpotContactResponse.Result(
            properties = HubSpotContactResponse.Result.Properties(
                company = "Yes Soft",
                createdate = "2019-10-30T03:30:17.883Z",
                lastmodifieddate = "2019-10-30T03:30:17.883Z",
                firstname = "Mohammad",
                lastname = "Al Kalaleeb",
                email = "mohammad.al.kalaleeb@gmail.com",
                phone = "+963123456789",
                website = "mohammad-kalaleeb@gmail.com"
            )
        )
    }

    fun getResponseItemsList(size: Int = 3): HubSpotContactResponse {
        val resultList = mutableListOf<HubSpotContactResponse.Result>()

        for (i in 0..size) {
            resultList.add(HubSpotContactResponse.Result(properties = getProperty()))
        }

        return HubSpotContactResponse(results = resultList)
    }

    private fun getProperty(): HubSpotContactResponse.Result.Properties {
        return HubSpotContactResponse.Result.Properties(
            company = "Yes Soft",
            createdate = "2019-10-30T03:30:17.883Z",
            lastmodifieddate = "2019-10-30T03:30:17.883Z",
            firstname = "Mohammad",
            lastname = "Al Kalaleeb",
            email = "mohammad.al.kalaleeb@gmail.com",
            phone = "+963123456789",
            website = "mohammad-kalaleeb@gmail.com"
        )
    }
}
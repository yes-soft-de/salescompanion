package de.sixbits.salescompanion.util

import de.sixbits.salescompanion.response.HubspotContactListResponse

object HubspotContactResponseFactory {
    fun getResponseItem(): HubspotContactListResponse.Result {
        return HubspotContactListResponse.Result(
            properties = HubspotContactListResponse.Result.Properties(
                company = "Yes Soft",
                createdate = "2019-10-30T03:30:17.883Z",
                lastmodifieddate = "2019-10-30T03:30:17.883Z",
                firstname = "Mohammad",
                lastname = "Al Kalaleeb",
                email = "mohammad.al.kalaleeb@gmail.com",
                phone = "+963123456789",
                website = "mohammad-kalaleeb@gmail.com",
                id = 1
            )
        )
    }

    fun getResponseItemsList(size: Int = 3): HubspotContactListResponse {
        val resultList = mutableListOf<HubspotContactListResponse.Result>()

        for (i in 0..size) {
            resultList.add(HubspotContactListResponse.Result(properties = getProperty()))
        }

        return HubspotContactListResponse(results = resultList, paging = HubspotContactListResponse.Paging(
            next = HubspotContactListResponse.Paging.Next(
                after = " ",
                link = " "
            )
        ))
    }

    private fun getProperty(): HubspotContactListResponse.Result.Properties {
        return HubspotContactListResponse.Result.Properties(
            company = "Yes Soft",
            createdate = "2019-10-30T03:30:17.883Z",
            lastmodifieddate = "2019-10-30T03:30:17.883Z",
            firstname = "Mohammad",
            lastname = "Al Kalaleeb",
            email = "mohammad.al.kalaleeb@gmail.com",
            phone = "+963123456789",
            website = "mohammad-kalaleeb@gmail.com",
            id = 1
        )
    }

    fun getCreateContactResponse(): HubspotContactListResponse {
        return HubspotContactListResponse(
            results = listOf(),
            paging = HubspotContactListResponse.Paging(
                next = HubspotContactListResponse.Paging.Next(
                    after = "",
                    link = ""
                )
            )
        )
    }
}
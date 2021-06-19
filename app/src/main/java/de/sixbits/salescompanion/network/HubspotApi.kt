package de.sixbits.salescompanion.network

import de.sixbits.salescompanion.BuildConfig
import de.sixbits.salescompanion.request.CreateEmailLogRequest
import de.sixbits.salescompanion.request.CreateHubSpotContactRequest
import de.sixbits.salescompanion.response.CreateEmailLogResponse
import de.sixbits.salescompanion.response.HubspotContactListResponse
import de.sixbits.salescompanion.response.HubSpotContactResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HubspotApi {
    @GET("/crm/v3/objects/contacts")
    fun getContacts(
        @Query("limit") limit: Int = 100,
        @Query("hapikey") apiKey: String = BuildConfig.HUBSPOT_API_KEY
    ): Observable<HubspotContactListResponse>

    @POST("/crm/v3/objects/contacts")
    fun createContact(
        @Body createContactRequest: CreateHubSpotContactRequest,
        @Query("hapikey") apiKey: String = BuildConfig.HUBSPOT_API_KEY
    ): Observable<HubSpotContactResponse>


    @POST("/engagements/v1/engagements")
    fun createLog(
        @Body createEmailLogRequest: CreateEmailLogRequest,
        @Query("hapikey") apiKey: String = BuildConfig.HUBSPOT_API_KEY
    ): Observable<CreateEmailLogResponse>
}
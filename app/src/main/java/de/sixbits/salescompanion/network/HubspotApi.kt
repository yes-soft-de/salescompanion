package de.sixbits.salescompanion.network

import de.sixbits.salescompanion.request.CreateHubSpotContactRequest
import de.sixbits.salescompanion.response.HubSpotContactResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HubspotApi {
    @GET("/crm/v3/objects/contacts")
    fun getContacts(): Observable<HubSpotContactResponse>

    @POST("/crm/v3/objects/contacts")
    fun createContact(@Body createContactRequest: CreateHubSpotContactRequest): Observable<HubSpotContactResponse>
}
package de.sixbits.salescompanion.network

import de.sixbits.salescompanion.request.CreateHubSpotContactRequest
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body

interface HubspotApi {
    fun getContacts(): Observable<HubspotApi>

    fun createContact(@Body createContactRequest: CreateHubSpotContactRequest): Observable<HubspotApi>
}
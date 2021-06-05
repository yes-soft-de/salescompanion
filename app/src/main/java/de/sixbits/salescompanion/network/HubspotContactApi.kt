package de.sixbits.salescompanion.network

import de.sixbits.salescompanion.request.CreateHubSpotContactRequest
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body

interface HubspotContactApi {
    fun getContacts(): Observable<HubspotContactApi>

    fun createContact(@Body createContactRequest: CreateHubSpotContactRequest): Observable<HubspotContactApi>
}
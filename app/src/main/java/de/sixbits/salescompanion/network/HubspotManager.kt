package de.sixbits.salescompanion.network

import de.sixbits.salescompanion.response.HubSpotContactResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class HubspotManager @Inject constructor(private val hubspotApi: HubspotApi){

    fun getContacts(): Observable<HubSpotContactResponse> {
        return hubspotApi.getContacts()
    }
}
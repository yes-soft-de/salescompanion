package de.sixbits.salescompanion.service

import android.util.Log
import de.sixbits.salescompanion.contacts.DeviceContactService
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.mapper.SalesContactMapper
import de.sixbits.salescompanion.network.HubspotApi
import de.sixbits.salescompanion.request.CreateHubSpotContactRequest
import de.sixbits.salescompanion.response.HubSpotContactResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

private const val TAG = "ContactService"

class ContactService @Inject constructor(
    private val deviceContactService: DeviceContactService,
    private val hubspotApi: HubspotApi
) {
    fun getNetworkContacts(): Observable<List<SalesContactDataModel>> {
        return hubspotApi.getContacts()
            .doOnNext {
                Log.d(TAG, "getNetworkContacts: ${it.results?.get(0)?.properties?.phone}")
            }
            .map {
                return@map SalesContactMapper.toSalesContactDataModelList(it)
            }
            .doOnNext {
                Log.d(TAG, "getNetworkContacts: ${it[0].phone}")
            }
    }

    fun getDeviceContacts(): Observable<List<SalesContactDataModel>> {
        return Observable.just(deviceContactService.getContacts())
    }

    fun createHubSpotContact(salesContact: SalesContactDataModel): Observable<HubSpotContactResponse> {
        return hubspotApi.createContact(
            CreateHubSpotContactRequest(
                properties = CreateHubSpotContactRequest.Properties(
                    company = "Yes Soft",
                    email = "",
                    firstname = salesContact.firstName,
                    lastname = salesContact.lastName,
                    phone = salesContact.phone,
                    website = ""
                )
            )
        )
    }
}
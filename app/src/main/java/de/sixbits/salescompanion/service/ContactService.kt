package de.sixbits.salescompanion.service

import android.util.Log
import de.sixbits.salescompanion.BuildConfig
import de.sixbits.salescompanion.contacts.DeviceContactService
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.mapper.SalesContactMapper
import de.sixbits.salescompanion.network.HubspotApi
import de.sixbits.salescompanion.request.CreateHubSpotContactRequest
import de.sixbits.salescompanion.response.HubSpotContactResponse
import de.sixbits.salescompanion.response.HubspotContactListResponse
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

private const val TAG = "ContactService"

class ContactService @Inject constructor(
    private val deviceContactService: DeviceContactService,
    private val hubspotApi: HubspotApi
) {
    fun getNetworkContacts(): Observable<List<SalesContactDataModel>> {
        return hubspotApi.getContacts()
            .map {
                SalesContactMapper.toSalesContactDataModelList(it)
            }
            .doOnNext {
                Log.d(TAG, "getNetworkContacts: ${it.size}")
            }
            .doOnError {
                Log.d(TAG, "getNetworkContacts: $it")
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
                    phone = "${salesContact.phone}",
                    website = ""
                )
            )
        )
    }

    fun searchContact(name: String): Observable<Long> {
        val deviceContactName = name.replace(" ", "")
        return hubspotApi.getContacts()
            .map {
                it.results.filter { resultItem ->
                    val networkName =
                        "${resultItem.properties.firstname} ${resultItem.properties.lastname}".replace(
                            " ",
                            ""
                        )
                    return@filter networkName == deviceContactName
                }
            }
            .map {
                if (it.isEmpty()) {
                    return@map -1
                } else {
                    it[0].properties.id
                }
            }
    }
}
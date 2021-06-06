package de.sixbits.salescompanion.service

import de.sixbits.salescompanion.contacts.DeviceContactService
import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.mapper.SalesContactMapper
import de.sixbits.salescompanion.network.HubspotManager
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ContactService @Inject constructor(
    private val deviceContactService: DeviceContactService,
    private val hubspotManager: HubspotManager
) {
    fun getNetworkContacts(): Observable<List<SalesContactDataModel>> {
        return hubspotManager.getContacts()
            .map {
                return@map SalesContactMapper.toSalesContactDataModelList(it)
            }
    }

    fun getDeviceContacts(): Observable<List<SalesContactDataModel>> {
        return Observable.just(deviceContactService.getContacts())
    }
}
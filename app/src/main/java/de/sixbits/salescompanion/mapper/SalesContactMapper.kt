package de.sixbits.salescompanion.mapper

import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.response.HubSpotContactResponse
import java.text.SimpleDateFormat
import java.util.*

object SalesContactMapper {
    fun toSalesContactDataModelList(
        response: HubSpotContactResponse,
    ): List<SalesContactDataModel> {
        return response.results.map {
            toSalesContactDataModel(it)
        }
    }

    fun toSalesContactDataModel(
        it: HubSpotContactResponse.Result
    ): SalesContactDataModel {
        val df = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.US);

        return SalesContactDataModel(
            firstName = it.properties.firstname,
            lastName = it.properties.lastname,
            email = it.properties.email,
            phone = it.properties.phone,
            createdAt = df.parse(it.properties.createdate),
            updatedAt = df.parse(it.properties.lastmodifieddate),
            company = it.properties.company
        )
    }
}
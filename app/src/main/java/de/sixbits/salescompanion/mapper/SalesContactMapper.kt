package de.sixbits.salescompanion.mapper

import de.sixbits.salescompanion.data_model.SalesContactDataModel
import de.sixbits.salescompanion.response.HubspotContactListResponse
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "SalesContactMapper"

object SalesContactMapper {
    fun toSalesContactDataModelList(
        response: HubspotContactListResponse,
    ): List<SalesContactDataModel> {
        return response.results.map {
            toSalesContactDataModel(it)
        }
    }

    private fun toSalesContactDataModel(
        it: HubspotContactListResponse.Result
    ): SalesContactDataModel {
        val df = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.US)

        val createdAt: Date? = try {
            df.parse(it.properties.createdate)
        } catch (e: Exception) {
            Calendar.getInstance().time
        }

        val updatedAt: Date? = try {
            df.parse(it.properties.lastmodifieddate)
        } catch (e: Exception) {
            Calendar.getInstance().time
        }

        return SalesContactDataModel(
            firstName = it.properties.firstname,
            lastName = it.properties.lastname,
            email = it.properties.email,
            phone = it.properties.phone,
            createdAt = createdAt,
            updatedAt = updatedAt,
            company = it.properties.company
        )
    }
}
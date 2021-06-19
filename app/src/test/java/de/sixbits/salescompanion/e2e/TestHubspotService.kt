package de.sixbits.salescompanion.e2e

import de.sixbits.salescompanion.config.Consts
import de.sixbits.salescompanion.network.HubspotApi
import de.sixbits.salescompanion.service.ChatLogService
import de.sixbits.salescompanion.service.ContactService
import de.sixbits.salescompanion.util.ChatLogFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TestHubspotService {
    val s = "Maria Johnson (Sample Contact)"

    @Test
    fun testLiveHubSpotApi() {
        val apiClient = Retrofit
            .Builder()
            .baseUrl(Consts.API_ROOT)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val hubspotApi = apiClient.create(HubspotApi::class.java)
        val contactService = mock<ContactService>()

        val service = ChatLogService(hubspotApi = hubspotApi, contactService = contactService)

    }
}
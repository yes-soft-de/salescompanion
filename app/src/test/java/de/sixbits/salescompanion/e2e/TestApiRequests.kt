package de.sixbits.salescompanion.e2e

import de.sixbits.salescompanion.config.Consts
import de.sixbits.salescompanion.network.HubspotApi
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
class TestApiRequests {

    @Test
    fun testLiveHubSpotApi() {
        val apiClient = Retrofit
            .Builder()
            .baseUrl(Consts.API_ROOT)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val hubspotApi = apiClient.create(HubspotApi::class.java)

        hubspotApi.getContacts().map {
            println("${it.results[0].properties.firstname}${it.results[0].properties.lastname}")
        }.test()
            .assertValue { true }
    }
}
package de.sixbits.salescompanion.network

import de.sixbits.salescompanion.response.HubSpotContactResponse
import de.sixbits.salescompanion.util.HubspotContactResponseFactory
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class HubspotManagerTest {

    lateinit var hubspotApi: HubspotApi

    @BeforeAll
    fun setup() {
        hubspotApi = mock(HubspotApi::class.java)
    }

    @Test
    fun getContacts() {
        // Given I have no contacts
        `when`(hubspotApi.getContacts()).thenReturn(
            Observable.just(
                HubSpotContactResponse(
                    results = listOf(),
                    paging = HubSpotContactResponse.Paging()
                )
            )
        )

        // When I request contacts
        hubspotApi.getContacts()
            .test()
            // Then I should get an empty List
            .assertValue {
                it.results.isEmpty()
            }

        // Given I have three contacts
        `when`(hubspotApi.getContacts()).thenReturn(
            Observable.just(
                HubspotContactResponseFactory.getResponseItemsList()
            )
        )

        // When I request contacts
        hubspotApi.getContacts()
            .test()
            // Then I should get a non empty List
            .assertValue {
                it.results.isNotEmpty()
            }
    }
}
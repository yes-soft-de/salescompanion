package de.sixbits.salescompanion.network

import de.sixbits.salescompanion.mapper.ChatLogMapper
import de.sixbits.salescompanion.request.CreateEmailLogRequest
import de.sixbits.salescompanion.util.ChatLogFactory
import de.sixbits.salescompanion.util.CreateEmailLogResponseFactory
import de.sixbits.salescompanion.util.HubspotContactResponseFactory
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
internal class HubspotApiTest {

    private lateinit var hubspotApi: HubspotApi

    @Test
    fun getContacts() {
        hubspotApi = mock(HubspotApi::class.java)

        // Given I have no contacts
        `when`(hubspotApi.getContacts()).thenReturn(
            Observable.just(
                HubspotContactResponseFactory.getCreateContactResponse()
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

    @Test
    fun testCreateLog() {
        hubspotApi = mock(HubspotApi::class.java)

        // Given I have a chat log
        `when`(hubspotApi.createLog(ChatLogMapper.toCreateEmailLogRequest(
            ChatLogFactory.getChatLog()
        ))).thenReturn(
            Observable.just(CreateEmailLogResponseFactory.getChatCreateResponse())
        )

        // When I request to save a log
        hubspotApi.createLog(
            ChatLogMapper.toCreateEmailLogRequest(
                ChatLogFactory.getChatLog()
            )
        ).test()
            // Then I should get an engagement Id to indicate that the request has been saved
            .assertValue {
                it.engagement.id != null
            }
    }
}
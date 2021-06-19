package de.sixbits.salescompanion.e2e

import de.sixbits.salescompanion.config.Consts
import de.sixbits.salescompanion.contacts.DeviceContactService
import de.sixbits.salescompanion.network.HubspotApi
import de.sixbits.salescompanion.service.ChatLogService
import de.sixbits.salescompanion.service.ContactService
import de.sixbits.salescompanion.util.ChatLogFactory
import de.sixbits.salescompanion.view_model.ticket.TicketViewModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
class TicketViewModelTestE2E {

    @Test
    fun testCreateTicket() {
        val apiClient = Retrofit
            .Builder()
            .baseUrl(Consts.API_ROOT)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val hubspotApi = apiClient.create(HubspotApi::class.java)
        val deviceContactService = mock<DeviceContactService>()

        val contactService =
            ContactService(hubspotApi = hubspotApi, deviceContactService = deviceContactService)
        val chatService = ChatLogService(hubspotApi = hubspotApi, contactService = contactService)

        val vm = TicketViewModel(contactService = contactService, chatLogService = chatService)

//        vm.saveNewLog(ChatLogFactory.getChatLog())
    }
}
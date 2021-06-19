package de.sixbits.salescompanion.util

import de.sixbits.salescompanion.response.CreateEmailLogResponse

object CreateEmailLogResponseFactory {
    fun getChatCreateResponse(): CreateEmailLogResponse {
        return CreateEmailLogResponse(
            engagement = CreateEmailLogResponse.Engagement(
                id = 1
            )
        )
    }
}
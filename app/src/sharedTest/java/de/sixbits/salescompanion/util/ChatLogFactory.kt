package de.sixbits.salescompanion.util

import de.sixbits.salescompanion.data_model.ChatMessageDataModel

object ChatLogFactory {
    fun getChatLog(): List<ChatMessageDataModel> {
        return listOf(
            ChatMessageDataModel(
                name = "Android",
                date = "11/11",
                time = "11:11",
                msg = "Message From Android"
            )
        )
    }
}
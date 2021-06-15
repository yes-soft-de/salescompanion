package de.sixbits.salescompanion.mapper

import de.sixbits.salescompanion.data_model.ChatMessageDataModel

object ChatMessagesMapper {
    fun toChatMessageDateModel(line: String): ChatMessageDataModel {
        return ChatMessageDataModel(
            date = line.substring(0, line.lastIndexOf(",")),
            time = line.substring(line.lastIndexOf(","), line.lastIndexOf("-")),
            name = line.substring(line.lastIndexOf("- "), line.lastIndexOf(":")),
            msg = line.substring(line.lastIndexOf(":"))
        )
    }
}
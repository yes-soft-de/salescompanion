package de.sixbits.salescompanion.mapper

import de.sixbits.salescompanion.data_model.ChatMessageDataModel

object ChatMessagesMapper {
    fun toChatMessageDateModel(line: String): ChatMessageDataModel {
        return ChatMessageDataModel(
            date = line.substring(0, line.lastIndexOf(",")).trim(),
            time = line.substring(line.lastIndexOf(",") + 1, line.lastIndexOf("-")).trim(),
            name = line.substring(line.lastIndexOf("- ") + 2, line.lastIndexOf(":")).trim(),
            msg = line.substring(line.lastIndexOf(":") + 1).trim(),
        )
    }
}
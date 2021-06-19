package de.sixbits.salescompanion.mapper

import android.util.Log
import de.sixbits.salescompanion.data_model.ChatMessageDataModel

private const val TAG = "ChatMessagesMapper"

object ChatMessagesMapper {
    fun toChatMessageDateModel(line: String): ChatMessageDataModel? {
        try {
            val date = line.substring(0, line.indexOf(","))
            val time = line.substring(line.indexOf(",") + 1, line.indexOf("-")).trim()
            val name = line.substring(
                line.indexOf("-") + 2,
                line.indexOf(":", "$date, $time -  ".length)
            )
            val msg = line.substring(line.indexOf(":", line.indexOf(":") + 1) + 2)
            return ChatMessageDataModel(
                date = date,
                time = time,
                name = name,
                msg = msg
            )
        } catch (e: Exception) {
            return null;
        }
    }

    fun toChatMessagesDateModelList(fullText: String): List<ChatMessageDataModel> {
        val messagesList = mutableListOf<ChatMessageDataModel>()
        fullText.split("\n").forEach {
            val msg = toChatMessageDateModel(it.trim())
            if (msg != null) {
                messagesList.add(msg)
            }
        }
        return messagesList
    }
}
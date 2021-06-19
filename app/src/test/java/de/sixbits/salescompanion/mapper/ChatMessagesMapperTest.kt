package de.sixbits.salescompanion.mapper

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChatMessagesMapperTest {

    @Test
    fun testMapper() {
        val input =
            "6/3/20, 00:45 - +963 959 379 692: اوك رح بلش صممن فتحتwebsite بالفجوال بلشت بالlogin"

        val message = ChatMessagesMapper.toChatMessageDateModel(input)

        assert(message.name.trim() == "+963 959 379 692")
        assert(message.date.trim() == "6/3/20")
        assert(message.time.trim() == "00:45")
        assert(message.msg.trim() == " اوك رح بلش صممن فتحتwebsite بالفجوال بلشت بالlogin".trim())
    }
}
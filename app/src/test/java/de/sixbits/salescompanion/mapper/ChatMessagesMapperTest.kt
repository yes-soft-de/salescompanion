package de.sixbits.salescompanion.mapper

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChatMessagesMapperTest {

    @Test
    fun testLineMapper() {
        val input =
            "6/3/20, 00:45 - +963 959 379 692: I Message"

        val message = ChatMessagesMapper.toChatMessageDateModel(input)

        println("${message?.date} | ${message?.time} | ${message?.name} | ${message?.msg}")

        assert(message?.name == "+963 959 379 692")
        assert(message?.date == "6/3/20")
        assert(message?.time == "00:45")
        assert(message?.msg == "I Message")
    }

    @Test
    fun testLineMapperExceptions() {
        val input = "I Message"

        val message = ChatMessagesMapper.toChatMessageDateModel(input)

        assert(message == null)
    }

    @Test
    fun testLogMapper() {
        val input =
            "6/3/20, 00:45 - +963 959 379 692: I Message\n6/3/20, 00:45 - +963 959 379 692: I Message"

        val message = ChatMessagesMapper.toChatMessagesDateModelList(input)

        assert(message.size == 2)
    }
}
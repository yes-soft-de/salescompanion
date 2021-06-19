package de.sixbits.salescompanion.templates

import de.sixbits.salescompanion.data_model.ChatMessageDataModel
import java.lang.StringBuilder

object EmailTemplates {
    /**
     * Generate an Email template for a given group of messages.
     */
    fun getHtmlEmailLog(messages: List<ChatMessageDataModel>, myName: String): String {
        val htmlBuilder = StringBuilder()

        htmlBuilder.append(getEmailHeader())

        messages.forEach {
            if (myName == it.name) {
                htmlBuilder.append("<div class=\"our-message\">${it.name}: ${it.msg}</div>")
            } else {
                htmlBuilder.append("<div class=\"their-message\">${it.name}: ${it.msg}</div>")
            }
        }

        htmlBuilder.append(getEmailFooter())

        return htmlBuilder.toString()
    }

    private fun getEmailHeader(): String {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Message List</title>\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\">\n" +
                "    <style>\n" +
                "        body {\n" +
                "            padding: 0;\n" +
                "            margin: 0;\n" +
                "            font-family: 'Poppins', sans-serif;\n" +
                "        }\n" +
                "        .message-header {\n" +
                "            display: flex;\n" +
                "            padding: 1rem;\n" +
                "            background-color: aquamarine;\n" +
                "            margin-bottom: 2rem;\n" +
                "        }\n" +
                "        .message-list {\n" +
                "            display: flex;\n" +
                "            flex-direction: column;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "        .our-message {\n" +
                "            text-align: end;\n" +
                "            background-color: blue;\n" +
                "            color: white;\n" +
                "            padding: .5rem;\n" +
                "            margin-bottom: .5rem;\n" +
                "            border-radius: 8px;\n" +
                "            align-self: flex-start;\n" +
                "            margin-left: 1rem;\n" +
                "        }\n" +
                "        .their-message {\n" +
                "            text-align: start;\n" +
                "            background-color: green;\n" +
                "            color: white;\n" +
                "            padding: .5rem;\n" +
                "            margin-bottom: .5rem;\n" +
                "            border-radius: 8px;\n" +
                "            align-self: flex-end;\n" +
                "            margin-right: 1rem;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"message-header\">\n" +
                "        Chat History between Mohammad and Ali\n" +
                "    </div>\n" +
                "    <div class=\"message-list\">";
    }

    private fun getEmailFooter(): String {
        return "</div>\n" +
                "</body>\n" +
                "\n" +
                "</html>"
    }
}
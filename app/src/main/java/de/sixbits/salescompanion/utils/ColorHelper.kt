package de.sixbits.salescompanion.utils

import android.graphics.Color

object ColorHelper {
    fun hashColor(str: String): Int {
        val ch = str.toCharArray()
        val sb = StringBuffer()
        for (i in ch.indices) {
            val hexString = Integer.toHexString(ch[i].code)
            sb.append(hexString)
        }
        var result = sb.toString()
        while (result.length <= 6) {
            result += "0"
        }
        if (result.length > 6) {
            result = result.substring(0, 6)
        }

        return Color.rgb(
            result.substring(0, 2).toInt(),
            result.substring(0, 2).toInt(),
            result.substring(0, 2).toInt()
        )
    }
}
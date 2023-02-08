package com.example.utils

import java.util.*

object Utils {
    fun getCalendar(): Calendar {
        val cal = Calendar.getInstance()
        cal.timeZone = TimeZone.getTimeZone("EST")
        cal.time = Date()
        return cal
    }

    fun formatDate(year: Int, month: Int, day: Int): String {
        return String.format("%d-%d-%d", year, month, day)
    }

    fun createToken(account: String, password: String): String {
        val message = "${account}_5566_${password}_${System.currentTimeMillis()}"
        return Base64.getEncoder().encodeToString(message.toByteArray())
    }
}
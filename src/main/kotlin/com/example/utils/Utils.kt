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
}
package com.tenty.tentyandroid.util

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    private val monthNames = arrayOf("January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December")

    /**
     * Returns a readable date from an epoch number
     * @param epoch Long
     * @return String
     */
    fun getDateFromEpoch(epoch: Long): String {
        // Get time
        val simpleDateFormat = SimpleDateFormat(" - hh:mm a", Locale.US)
        simpleDateFormat.timeZone = TimeZone.getDefault()
        val time = simpleDateFormat.format(epoch)

        // Create calendar instance
        val cal = Calendar.getInstance()
        cal.timeInMillis = epoch

        return monthNames[cal.get(Calendar.MONTH)] + " " +
            cal.get(Calendar.DAY_OF_MONTH).toString() + ", " +
            cal.get(Calendar.YEAR).toString() + time
    }
}
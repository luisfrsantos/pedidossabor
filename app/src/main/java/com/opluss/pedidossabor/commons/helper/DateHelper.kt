package com.opluss.pedidossabor.commons.helper

import com.google.firebase.Timestamp
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat.forPattern

const val BR_PATTERN_DISPLAY = "dd/MM/yyyy HH:mm:ss"

object DateHelper {
    fun now(pattern: String): String = DateTime.now().toString(forPattern(pattern))

    fun minusDays(days: Int, pattern: String): String = DateTime.now().minusDays(days).toString(forPattern(pattern))

    fun toTimesTamp(value: String, pattern: String): Timestamp =
        Timestamp(DateTime.parse(value, forPattern(pattern)).toDate())

    fun timesTampToString(value: Timestamp, pattern: String): String =
        DateTime(value.seconds).toString(forPattern(pattern))
}
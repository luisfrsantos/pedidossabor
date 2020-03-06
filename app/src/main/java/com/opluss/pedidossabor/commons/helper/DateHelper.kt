package com.opluss.pedidossabor.commons.helper

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.sql.Timestamp

const val BR_PATTERN_DISPLAY = "dd/MM/yyyy HH:mm:ss"

object DateHelper {
    fun now(pattern: String): String {
        return DateTime.now().toString(DateTimeFormat.forPattern(pattern))
    }

    fun toTimesTamp(value: String, pattern: String): Timestamp {
        return Timestamp(DateTime.parse(value, DateTimeFormat.forPattern(pattern)).toDate().time)
    }
}
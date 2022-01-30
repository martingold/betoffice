package cz.uhk.ppro.betoffice.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {

	fun parseDate(date: String?): Date? {
		if (date === null) {
			return null
		}

		val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
		return formatter.parse(date)
	}

	fun parseDateTime(date: String?): Date? {
		if (date === null) {
			return null
		}

		return SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(date)
	}
}

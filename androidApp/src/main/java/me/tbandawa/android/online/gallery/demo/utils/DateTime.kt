package me.tbandawa.android.online.gallery.demo.utils

import java.text.SimpleDateFormat
import java.util.*

const val HH_MM = "HH:mm"
const val HH_MM_SS = "HH:mm:ss"
const val YYYY_MM_DD = "yyyy-MM-dd"
const val YYYY_MM_DDS = "yyyy/MM/dd"
const val MMM_DD_YYYY = "MMM dd, yyyy"
const val DD_MMM_YYYY = "dd MMM yyyy"
const val EEE_DD_MMM_YYYY = "EEE dd MMM yyyy"
const val YYYY_MM_DD_T = "yyyy-MM-dd'T'HH:mm:ss"

fun convertDate(from: String, to: String, date: String): String {
    val fromFormat = SimpleDateFormat(from, Locale.ENGLISH)
    val toFormat = SimpleDateFormat(to, Locale.ENGLISH)
    return toFormat.format(fromFormat.parse(date)!!)
}
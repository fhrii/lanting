package academy.bangkit.lanting.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    private var fileNameProfileFormat = SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault())
    fun generateFileName() = fileNameProfileFormat.format(Date())

    private var dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    fun today() = dateFormat.format(Date())
    fun todayTimeStamp() = dateFormat.parse(today())
    fun formatDateToString(date: Date): String = dateFormat.format(date)
    fun formatStringToDate(date: String): Date = dateFormat.parse(date) as Date
}
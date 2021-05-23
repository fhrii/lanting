package academy.bangkit.lanting.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    private var fileNameProfileFormat = SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault())
    fun generateFileName() = fileNameProfileFormat.format(Date())

    private var dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    fun today() = dateFormat.format(Date())
    fun todayTimeStamp() = dateFormat.parse(today()) as Date
    fun formatDateToString(date: Date) = dateFormat.format(date)
    fun formatStringToDate(date: String) = dateFormat.parse(date) as Date

    fun getProfileAge(date: Date, field: Int = Calendar.YEAR): Int {
        val calendar = Calendar.getInstance()
        val time = todayTimeStamp().time - date.time
        calendar.time = Date(time)

        val year = calendar.get(Calendar.YEAR) - 1970
        val month = calendar.get(Calendar.MONTH) + (year * 12)

        return if (field == Calendar.YEAR) year else month
    }
}
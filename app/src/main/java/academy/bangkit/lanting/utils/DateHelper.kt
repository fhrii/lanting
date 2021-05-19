package academy.bangkit.lanting.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    private var dateOfBirthFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    fun formatDateOfBirthToString(date: Date): String = dateOfBirthFormat.format(date)

    private var fileNameProfileFormat = SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault())
    fun generateFileName() = fileNameProfileFormat.format(Date())

    private var dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    fun today() = dateFormat.format(Date())
}
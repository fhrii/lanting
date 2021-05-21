package academy.bangkit.lanting.data.local

import academy.bangkit.lanting.data.model.ProfileCategory
import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun toTimestamp(value: Date): Long {
        return value.time
    }

    @TypeConverter
    fun fromCategory(value: ProfileCategory): String {
        return value.name
    }

    @TypeConverter
    fun toCategory(value: String): ProfileCategory {
        return enumValueOf(value)
    }
}
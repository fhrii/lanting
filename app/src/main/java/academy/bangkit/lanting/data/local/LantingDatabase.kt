package academy.bangkit.lanting.data.local

import academy.bangkit.lanting.data.local.entity.NutritionEntity
import academy.bangkit.lanting.data.local.entity.ProfileEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ProfileEntity::class, NutritionEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LantingDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        const val DATABASE_NAME = "lanting_db"
        const val TABLE_PROFILE_NAME = "profile"
        const val TABLE_NUTRITION_NAME = "nutrition"
    }
}
package academy.bangkit.lanting.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProfileEntity::class], version = 1, exportSchema = false)
abstract class LantingDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        const val DATABASE_NAME = "lanting_db"
        const val TABLE_PROFILE_NAME = "profile"
    }
}
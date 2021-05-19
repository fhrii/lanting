package academy.bangkit.lanting.data.local

import androidx.room.*

@Dao
interface ProfileDao {
    @Query("SELECT * FROM ${LantingDatabase.TABLE_PROFILE_NAME}")
    suspend fun getProfiles(): List<ProfileEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)

    @Update
    suspend fun updateProfile(profile: ProfileEntity)

    @Delete
    suspend fun deleteProfile(profile: ProfileEntity)
}
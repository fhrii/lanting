package academy.bangkit.lanting.data.local

import academy.bangkit.lanting.data.local.entity.NutritionEntity
import academy.bangkit.lanting.data.local.entity.ProfileEntity
import academy.bangkit.lanting.data.local.entity.ProfileWithNutritions
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

    @Transaction
    @Query("SELECT * FROM  ${LantingDatabase.TABLE_PROFILE_NAME} WHERE id = :profileId")
    suspend fun getProfileWithNutrition(profileId: Int): ProfileWithNutritions

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNutrients(nutritionEntities: List<NutritionEntity>)
}
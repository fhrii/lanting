package academy.bangkit.lanting.data.local.entity

import academy.bangkit.lanting.data.local.LantingDatabase
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = LantingDatabase.TABLE_NUTRITION_NAME)
data class NutritionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @NonNull
    val profileId: Int,

    @NonNull
    val date: Date,

    @NonNull
    val name: String,

    @NonNull
    val size: String,

    @NonNull
    val energy: Int,

    @NonNull
    val fat: Double,

    @NonNull
    val protein: Double,

    @NonNull
    val carbohydrate: Double
)
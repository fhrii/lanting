package academy.bangkit.lanting.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ProfileWithNutritions(
    @Embedded val profile: ProfileEntity,
    @Relation(parentColumn = "id", entityColumn = "profileId")
    val nutrients: List<NutritionEntity>
)

package academy.bangkit.lanting.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NeededNutrition(
    val energy: Int,
    val protein: Double,
    val fat: Double,
    val carbohydrate: Double
): Parcelable
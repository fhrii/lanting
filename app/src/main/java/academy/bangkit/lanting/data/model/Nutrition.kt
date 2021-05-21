package academy.bangkit.lanting.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Nutrition(
    val id: Int = 0,
    val profileId: Int,
    val date: Date,
    val name: String,
    val size: String,
    val energy: Int,
    val fat: Double,
    val protein: Double,
    val carbohydrate: Double
): Parcelable
package academy.bangkit.lanting.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val name: String,
    val ingredients: String,
    val howToCook: String,
    val nutrients: String,
    val seasonings: String?,
    val imageUrl: String,
    val price: Int
) : Parcelable
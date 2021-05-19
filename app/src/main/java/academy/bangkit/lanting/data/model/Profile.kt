package academy.bangkit.lanting.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    val id: Int,
    var name: String,
    var dateOfBirth: String,
    var height: Int,
    var weight: Int,
    var allergy: String?,
    var category: String,
    var picture: String?,
) : Parcelable
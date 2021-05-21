package academy.bangkit.lanting.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Profile(
    val id: Int,
    var name: String,
    var dateOfBirth: Date,
    var height: Int,
    var weight: Int,
    var allergy: String?,
    var category: ProfileCategory,
    var picture: String?,
) : Parcelable
package academy.bangkit.lanting.data.model

import androidx.annotation.DrawableRes

data class AboutProfile(
    val name: String,
    val path: String,
    val univ: String,
    @DrawableRes
    val photo: Int
)

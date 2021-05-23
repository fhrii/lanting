package academy.bangkit.lanting.data.model

data class ProfileMember(
    val minAge: Int,
    val maxAge: Int,
    val category: ProfileCategory,
    val weight: Int,
    val height: Int,
    val energy: Int,
    val protein: Double,
    val fat: Double,
    val carbohydrate: Double,
)

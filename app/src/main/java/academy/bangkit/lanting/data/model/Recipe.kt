package academy.bangkit.lanting.data.model

data class Recipe(
    val name: String,
    val ingridients: String,
    val howToCook: String,
    val nutrients: String,
    val seasonings: String?,
    val imageUrl: String,
    val price: Int
)
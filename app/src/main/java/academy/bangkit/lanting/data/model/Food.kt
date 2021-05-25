package academy.bangkit.lanting.data.model

data class Food(
    val id: Int,
    val name: String,
    val size: List<FoodSize>
)

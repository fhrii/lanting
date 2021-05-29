package academy.bangkit.lanting.data.remote.response

import com.google.gson.annotations.SerializedName

data class FoodSizeResponse(
    @SerializedName("id_nutrisi")
    val id: Int,

    @SerializedName("value")
    val value: String,

    @SerializedName("energi")
    val energy: Int,

    @SerializedName("lemak")
    val fat: Double,

    @SerializedName("protein")
    val protein: Double,

    @SerializedName("karbohidrat")
    val carbohydrate: Double
)

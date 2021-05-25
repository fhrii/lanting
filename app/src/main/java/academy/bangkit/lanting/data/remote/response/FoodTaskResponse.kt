package academy.bangkit.lanting.data.remote.response

import com.google.gson.annotations.SerializedName

data class FoodTaskResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nama")
    val name: String,

    @SerializedName("ukuran")
    val size: List<FoodSizeResponse>
)

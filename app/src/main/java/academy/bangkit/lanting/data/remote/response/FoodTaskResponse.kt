package academy.bangkit.lanting.data.remote.response

import com.google.gson.annotations.SerializedName

data class FoodTaskResponse(
    @SerializedName("id_makanan")
    val id: Int,

    @SerializedName("nama_makanan")
    val name: String,

    @SerializedName("ukuran")
    val size: List<FoodSizeResponse>
)

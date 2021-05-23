package academy.bangkit.lanting.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("nama_makanan")
    val name: String,

    @SerializedName("bahan")
    val ingridients: String,

    @SerializedName("cara_memasak")
    val howToCook: String,

    @SerializedName("nutrisi")
    val nutrients: String,

    @SerializedName("bumbu")
    val seasonings: String?,

    @SerializedName("url")
    val imageUrl: String,

    @SerializedName("total_harga")
    val price: Int
)

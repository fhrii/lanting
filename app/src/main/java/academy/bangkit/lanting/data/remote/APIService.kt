package academy.bangkit.lanting.data.remote

import academy.bangkit.lanting.data.remote.response.FoodTaskResponse
import academy.bangkit.lanting.data.remote.response.RecipeResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface APIService {
    @GET("resep")
    fun getRecipes(): Call<List<RecipeResponse>>

    @Multipart
    @POST("detect")
    fun uploadTaskImage(@Part image: MultipartBody.Part): Call<List<FoodTaskResponse>>
}
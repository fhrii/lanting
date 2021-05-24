package academy.bangkit.lanting.data.remote

import academy.bangkit.lanting.data.remote.response.FoodTaskResponse
import academy.bangkit.lanting.data.remote.response.RecipeResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("af5e0cb7-f782-4a70-b5ce-22440d9bbd79")
    fun getRecipes(): Call<List<RecipeResponse>>

    @GET("2ea3ef7b-7eb7-469a-817a-303dc71c509e")
    fun getFoodTask(): Call<List<FoodTaskResponse>>
}
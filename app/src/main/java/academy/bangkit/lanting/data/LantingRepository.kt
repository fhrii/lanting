package academy.bangkit.lanting.data

import academy.bangkit.lanting.data.local.ProfileDao
import academy.bangkit.lanting.data.local.mapper.NutritionMapper
import academy.bangkit.lanting.data.local.mapper.ProfileMapper
import academy.bangkit.lanting.data.model.Nutrition
import academy.bangkit.lanting.data.model.Profile
import academy.bangkit.lanting.data.model.Recipe
import academy.bangkit.lanting.data.remote.APIService
import academy.bangkit.lanting.data.remote.mapper.RecipeMapper
import academy.bangkit.lanting.data.remote.response.RecipeResponse
import academy.bangkit.lanting.utils.ResultState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Exception

class LantingRepository(
    private val profileDao: ProfileDao,
    private val apiService: APIService,
    private val profileMapper: ProfileMapper,
    private val nutritionMapper: NutritionMapper,
    private val recipeMapper: RecipeMapper
) {
    fun insertProfile(profile: Profile, callback: (ResultState<Boolean>) -> Unit) {
        callback(ResultState.Loading)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val profileEntity = profileMapper.mapToEntity(profile)
                profileDao.insertProfile(profileEntity)
                withContext(Dispatchers.Main) {
                    callback(ResultState.Success(true))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback(ResultState.Error(e))
                }
            }
        }
    }

    fun updateProfile(profile: Profile, callback: (ResultState<Boolean>) -> Unit) {
        callback(ResultState.Loading)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val profileEntity = profileMapper.mapToEntity(profile)
                profileDao.updateProfile(profileEntity)
                withContext(Dispatchers.Main) {
                    callback(ResultState.Success(true))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback(ResultState.Error(e))
                }
            }
        }
    }

    fun deleteProfile(profile: Profile, callback: (ResultState<Boolean>) -> Unit) {
        callback(ResultState.Loading)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val profileEntity = profileMapper.mapToEntity(profile)
                profileDao.deleteProfile(profileEntity)
                withContext(Dispatchers.Main) {
                    callback(ResultState.Success(true))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback(ResultState.Error(e))
                }
            }
        }
    }

    fun getProfiles(): LiveData<ResultState<List<Profile>>> {
        val result = MutableLiveData<ResultState<List<Profile>>>()
        result.value = ResultState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val localProfiles = profileDao.getProfiles()
                val profiles = profileMapper.mapFromEntityList(localProfiles)
                withContext(Dispatchers.Main) {
                    result.value = ResultState.Success(profiles)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    result.value = ResultState.Error(e)
                }
            }
        }

        return result
    }

    fun getNutrients(profileId: Int): LiveData<ResultState<List<Nutrition>>> {
        val result = MutableLiveData<ResultState<List<Nutrition>>>()
        result.value = ResultState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val localProfile = profileDao.getProfileWithNutrition(profileId)
                val nutrients = nutritionMapper.mapFromEntityList(localProfile.nutrients)
                withContext(Dispatchers.Main) {
                    result.value = ResultState.Success(nutrients)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    result.value = ResultState.Error(e)
                }
            }
        }

        return result
    }

    fun insertNutrients(nutrients: List<Nutrition>, callback: (ResultState<Boolean>) -> Unit) {
        callback(ResultState.Loading)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val nutritionEntities = nutritionMapper.mapToEntitiyList(nutrients)
                profileDao.insertNutrients(nutritionEntities)
                withContext(Dispatchers.Main) {
                    callback(ResultState.Success(true))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback(ResultState.Error(e))
                }
            }
        }
    }

    fun getRecipes(): LiveData<ResultState<List<Recipe>>> {
        val recipeResult = MutableLiveData<ResultState<List<Recipe>>>()
        recipeResult.value = ResultState.Loading

        apiService.getRecipes().enqueue(object : Callback<List<RecipeResponse>> {
            override fun onResponse(
                call: Call<List<RecipeResponse>>,
                response: Response<List<RecipeResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val recipes = recipeMapper.mapFromEntityList(it)
                        recipeResult.value = ResultState.Success(recipes)
                    }
                }
            }

            override fun onFailure(call: Call<List<RecipeResponse>>, t: Throwable) {
                recipeResult.value = ResultState.Error(Exception(t))
            }
        })

        return recipeResult
    }
}
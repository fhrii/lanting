package academy.bangkit.lanting.di

import academy.bangkit.lanting.data.LantingRepository
import academy.bangkit.lanting.data.local.ProfileDao
import academy.bangkit.lanting.data.local.mapper.NutritionMapper
import academy.bangkit.lanting.data.local.mapper.ProfileMapper
import academy.bangkit.lanting.data.remote.APIService
import academy.bangkit.lanting.data.remote.mapper.FoodMapper
import academy.bangkit.lanting.data.remote.mapper.RecipeMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideLantingRepository(
        profileDao: ProfileDao,
        apiService: APIService,
        profileMapper: ProfileMapper,
        nutritionMapper: NutritionMapper,
        recipeMapper: RecipeMapper,
        foodMapper: FoodMapper
    ): LantingRepository {
        return LantingRepository(
            profileDao,
            apiService,
            profileMapper,
            nutritionMapper,
            recipeMapper,
            foodMapper
        )
    }
}
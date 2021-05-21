package academy.bangkit.lanting.di

import academy.bangkit.lanting.data.LantingRepository
import academy.bangkit.lanting.data.local.ProfileDao
import academy.bangkit.lanting.data.local.mapper.NutritionMapper
import academy.bangkit.lanting.data.local.mapper.ProfileMapper
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
        profileMapper: ProfileMapper,
        nutritionMapper: NutritionMapper
    ): LantingRepository {
        return LantingRepository(profileDao, profileMapper, nutritionMapper)
    }
}
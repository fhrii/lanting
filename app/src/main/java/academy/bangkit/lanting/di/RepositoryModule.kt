package academy.bangkit.lanting.di

import academy.bangkit.lanting.data.LantingRepository
import academy.bangkit.lanting.data.local.ProfileDao
import academy.bangkit.lanting.data.local.ProfileMapper
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
        profileMapper: ProfileMapper
    ): LantingRepository {
        return LantingRepository(profileDao, profileMapper)
    }
}
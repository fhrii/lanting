package academy.bangkit.lanting.di

import academy.bangkit.lanting.data.remote.RemoteAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    fun provideAPIService() = RemoteAPI.Service
}
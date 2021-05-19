package academy.bangkit.lanting.di

import academy.bangkit.lanting.data.ProfilePreferences
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context): ProfilePreferences {
        return ProfilePreferences(context)
    }
}
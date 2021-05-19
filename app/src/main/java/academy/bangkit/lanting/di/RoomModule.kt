package academy.bangkit.lanting.di

import academy.bangkit.lanting.data.local.LantingDatabase
import academy.bangkit.lanting.data.local.ProfileDao
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideLantingDatabase(@ApplicationContext context: Context): LantingDatabase {
        return Room.databaseBuilder(
            context,
            LantingDatabase::class.java,
            LantingDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideProfileDao(lantingDatabase: LantingDatabase): ProfileDao {
        return lantingDatabase.profileDao()
    }
}
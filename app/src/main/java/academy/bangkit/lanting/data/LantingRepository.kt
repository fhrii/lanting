package academy.bangkit.lanting.data

import academy.bangkit.lanting.data.local.ProfileDao
import academy.bangkit.lanting.data.local.ProfileMapper
import academy.bangkit.lanting.data.model.Profile
import academy.bangkit.lanting.utils.ResultState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LantingRepository(
    private val profileDao: ProfileDao,
    private val profileMapper: ProfileMapper,
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
}
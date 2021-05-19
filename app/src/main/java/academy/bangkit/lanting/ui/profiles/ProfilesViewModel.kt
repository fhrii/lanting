package academy.bangkit.lanting.ui.profiles

import academy.bangkit.lanting.data.LantingRepository
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfilesViewModel @Inject constructor(
    private val repository: LantingRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun getProfiles() = repository.getProfiles()
}
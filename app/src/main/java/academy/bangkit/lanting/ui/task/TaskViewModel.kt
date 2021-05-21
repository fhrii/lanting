package academy.bangkit.lanting.ui.task

import academy.bangkit.lanting.data.LantingRepository
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: LantingRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun getNutritions(profileId: Int) = repository.getNutrients(profileId)
}
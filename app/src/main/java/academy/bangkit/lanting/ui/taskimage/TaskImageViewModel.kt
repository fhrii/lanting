package academy.bangkit.lanting.ui.taskimage

import academy.bangkit.lanting.data.LantingRepository
import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskImageViewModel @Inject constructor(
    private val repository: LantingRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun getFoods(image: Bitmap) = repository.getFoodTask(image)
}
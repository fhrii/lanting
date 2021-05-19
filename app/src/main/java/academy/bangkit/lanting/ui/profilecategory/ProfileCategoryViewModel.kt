package academy.bangkit.lanting.ui.profilecategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileCategoryViewModel @Inject constructor() : ViewModel() {
    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    fun setCategory(category: String) {
        _category.value = category
    }
}
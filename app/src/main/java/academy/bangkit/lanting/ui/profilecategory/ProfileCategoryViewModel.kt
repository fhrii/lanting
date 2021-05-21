package academy.bangkit.lanting.ui.profilecategory

import academy.bangkit.lanting.data.model.ProfileCategory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileCategoryViewModel @Inject constructor() : ViewModel() {
    private val _category = MutableLiveData<ProfileCategory>()
    val category: LiveData<ProfileCategory> = _category

    fun setCategory(category: ProfileCategory) {
        _category.value = category
    }
}
package academy.bangkit.lanting.ui.profilenew

import academy.bangkit.lanting.data.LantingRepository
import academy.bangkit.lanting.data.model.Profile
import academy.bangkit.lanting.utils.ResultState
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ProfileNewViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: LantingRepository
) : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _dateOfBirth = MutableLiveData<Date>()
    val dateOfBirth: LiveData<Date> = _dateOfBirth

    private val _height = MutableLiveData<Int>()
    val height: LiveData<Int> = _height

    private val _weight = MutableLiveData<Int>()
    val weight: LiveData<Int> = _weight

    private val _allergy = MutableLiveData<String>()
    val allergy: LiveData<String> = _allergy

    private val _picture = MutableLiveData<Bitmap>()
    val picture: LiveData<Bitmap> = _picture

    fun setName(name: String) {
        _name.value = name
    }

    fun setDateOfBirth(birth: Date) {
        _dateOfBirth.value = birth
    }

    fun setHeight(height: Int) {
        _height.value = height
    }

    fun setWeight(weight: Int) {
        _weight.value = weight
    }

    fun setAllergy(allergy: String) {
        _allergy.value = allergy
    }

    fun setPicture(pic: Bitmap) {
        _picture.value = pic
    }

    fun insertProfile(profile: Profile, callback: (ResultState<Boolean>) -> Unit) {
        repository.insertProfile(profile, callback)
    }

    fun updateProfile(profile: Profile, callback: (ResultState<Boolean>) -> Unit) {
        repository.updateProfile(profile, callback)
    }

    fun deleteProfile(profile: Profile, callback: (ResultState<Boolean>) -> Unit) {
        repository.deleteProfile(profile, callback)
    }
}
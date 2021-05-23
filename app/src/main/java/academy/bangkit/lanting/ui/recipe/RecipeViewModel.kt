package academy.bangkit.lanting.ui.recipe

import academy.bangkit.lanting.data.LantingRepository
import academy.bangkit.lanting.data.model.Recipe
import academy.bangkit.lanting.utils.ResultState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: LantingRepository) : ViewModel() {
    var recipes: LiveData<ResultState<List<Recipe>>>

    init {
        recipes = repository.getRecipes()
    }
}
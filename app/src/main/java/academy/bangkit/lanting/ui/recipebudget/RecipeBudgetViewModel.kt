package academy.bangkit.lanting.ui.recipebudget

import academy.bangkit.lanting.data.model.Recipe
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeBudgetViewModel @Inject constructor() : ViewModel() {
    private val _budget = MutableLiveData<Int>()
    val budget: LiveData<Int> = _budget

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    fun setBudget(budget: Int) {
        _budget.value = budget
    }

    fun setRecipes(recipes: List<Recipe>) {
        this._recipes.value = recipes
    }
}
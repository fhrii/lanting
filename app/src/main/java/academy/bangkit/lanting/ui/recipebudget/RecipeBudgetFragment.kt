package academy.bangkit.lanting.ui.recipebudget

import academy.bangkit.lanting.data.ProfilePreferences
import academy.bangkit.lanting.data.model.Recipe
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.bangkit.lanting.databinding.FragmentRecipeBudgetBinding
import academy.bangkit.lanting.ui.recipe.RecipeViewModel
import academy.bangkit.lanting.utils.ResultState
import academy.bangkit.lanting.utils.setOnChangeListener
import academy.bangkit.lanting.utils.setVisible
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeBudgetFragment : Fragment() {
    private lateinit var binding: FragmentRecipeBudgetBinding
    private lateinit var adapter: RecipeBudgetAdapter
    private val viewModel: RecipeViewModel by activityViewModels()
    private val recipeBudgetViewModel: RecipeBudgetViewModel by viewModels()

    private var recipes: List<Recipe>? = null

    @Inject
    lateinit var profilePreferences: ProfilePreferences

    companion object {
        private const val TAG = "RecipeBudgetFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataLoading(true)
        setAdapter()
        setRecipes()
        setBudget()
    }

    private fun setDataLoading(state: Boolean) {
        binding.rvRecipes.setVisible(!state)
        binding.pbLoading.setVisible(state)
    }

    private fun setAdapter() {
        adapter = RecipeBudgetAdapter()
        binding.rvRecipes.layoutManager = LinearLayoutManager(this.context)
        binding.rvRecipes.adapter = adapter
    }

    private fun setRecipes() {
        viewModel.recipes.observe(requireActivity()) { result ->
            when (result) {
                is ResultState.Success -> {
                    profilePreferences.profile?.also { profile ->
                        val newRecipes = result.data.filter { it.category == profile.category }
                        recipeBudgetViewModel.setRecipes(newRecipes)
                        recipes = newRecipes
                        setDataLoading(false)
                    }
                }
                is ResultState.Error -> {
                    Log.d(TAG, "setRecipes: ${result.exception}")
                }
                is ResultState.Loading -> {
                    Log.d(TAG, "setRecipes: Loading")
                }
            }
        }

        recipeBudgetViewModel.recipes.observe(this.viewLifecycleOwner) {
            adapter.setRecipes(it)
        }
    }

    private fun setBudget() {
        binding.edtBudget.setOnChangeListener { budget ->
            var newBudget = 0
            if (!budget.isNullOrEmpty()) newBudget = budget.toInt()
            recipeBudgetViewModel.setBudget(newBudget)
            recipes?.filter { it.price <= newBudget }?.also { newRecipes ->
                recipeBudgetViewModel.setRecipes(newRecipes)
            }
        }

        recipeBudgetViewModel.budget.observe(this.viewLifecycleOwner) {
            if (it.toString() != binding.edtBudget.text.toString())
                binding.edtBudget.setText(it.toString())
        }
    }
}
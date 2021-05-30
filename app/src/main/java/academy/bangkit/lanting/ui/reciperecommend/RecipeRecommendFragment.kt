package academy.bangkit.lanting.ui.reciperecommend

import academy.bangkit.lanting.data.ProfilePreferences
import academy.bangkit.lanting.data.model.ProfileCategory
import academy.bangkit.lanting.databinding.FragmentRecipeRecommendBinding
import academy.bangkit.lanting.ui.recipe.RecipeViewModel
import academy.bangkit.lanting.utils.ResultState
import academy.bangkit.lanting.utils.setVisible
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeRecommendFragment : Fragment() {
    private lateinit var binding: FragmentRecipeRecommendBinding
    private lateinit var adapter: RecipeRecommendAdapter
    private val viewModel: RecipeViewModel by activityViewModels()

    @Inject
    lateinit var profilePreferences: ProfilePreferences

    companion object {
        private const val TAG = "RecipeRecommendFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeRecommendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataLoading(true)
        setAdapter()
        setRecipes()
    }

    private fun setDataLoading(state: Boolean) {
        binding.rvRecipes.setVisible(!state)
        binding.pbLoading.setVisible(state)
    }

    private fun setAdapter() {
        adapter = RecipeRecommendAdapter()
        binding.rvRecipes.layoutManager = LinearLayoutManager(this.context)
        binding.rvRecipes.adapter = adapter
    }

    private fun setRecipes() {
        viewModel.recipes.observe(requireActivity()) { result ->
            when (result) {
                is ResultState.Success -> {
                    profilePreferences.profile?.also { profile ->
                        val newRecipes = result.data.filter {
                            val category = when (profile.category) {
                                ProfileCategory.BADUTA -> ProfileCategory.BADUTA
                                else -> ProfileCategory.IBU
                            }
                            it.category == category
                        }
                        adapter.setRecipes(newRecipes)
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
    }
}
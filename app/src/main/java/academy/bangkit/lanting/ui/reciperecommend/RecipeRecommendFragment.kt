package academy.bangkit.lanting.ui.reciperecommend

import academy.bangkit.lanting.data.ProfilePreferences
import academy.bangkit.lanting.data.model.ProfileCategory
import academy.bangkit.lanting.databinding.FragmentRecipeRecommendBinding
import academy.bangkit.lanting.utils.DummyData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeRecommendFragment : Fragment() {
    private lateinit var binding: FragmentRecipeRecommendBinding
    private lateinit var adapter: RecipeRecommendAdapter

    @Inject
    lateinit var profilePreferences: ProfilePreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeRecommendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecipeRecommendAdapter()

        binding.rvRecipes.layoutManager = LinearLayoutManager(this.context)
        binding.rvRecipes.adapter = adapter

        val recipes = DummyData.getRecipes().filter {
            val category =
                if (profilePreferences.profile?.category == ProfileCategory.BADUTA) ProfileCategory.BADUTA else ProfileCategory.IBU
            it.category == category
        }

        adapter.setRecipes(recipes)
    }
}
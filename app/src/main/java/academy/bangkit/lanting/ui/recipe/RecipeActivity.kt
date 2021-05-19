package academy.bangkit.lanting.ui.recipe

import academy.bangkit.lanting.R
import academy.bangkit.lanting.databinding.ActivityRecipeBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayoutMediator

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding

    companion object {
        @StringRes
        private val TAB_TITLES = listOf(R.string.tab_recipe_text_1, R.string.tab_recipe_text_2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeAdapter = RecipeAdapter(this)
        binding.viewPager.adapter = recipeAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
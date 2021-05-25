package academy.bangkit.lanting.ui.recipedetail

import academy.bangkit.lanting.R
import academy.bangkit.lanting.data.model.Recipe
import academy.bangkit.lanting.databinding.ActivityRecipeDetailBinding
import academy.bangkit.lanting.utils.setImageFromUrl
import academy.bangkit.lanting.utils.setVisible
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeDetailBinding
    private var recipe: Recipe? = null

    companion object {
        const val EXTRA_RECIPE = "extra_recipe"

        @StringRes
        private const val ESTIMATED_PRICE_TEXT = R.string.estimated_price
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipe = intent.getParcelableExtra(EXTRA_RECIPE)
        recipe?.also {
            with(binding) {
                tvTitle.text = it.name
                tvPrice.text = getString(ESTIMATED_PRICE_TEXT, it.price)
                tvIngredient.text = it.ingredients
                ivRecipeImage.setImageFromUrl(it.imageUrl)
                it.seasonings.also {
                    when (it) {
                        null -> {
                            tvSeasoning.setVisible(false)
                            tvSeasoningText.setVisible(false)
                        }
                        else -> tvSeasoning.text = it
                    }
                }
                tvNutrition.text = it.nutrients
            }
        }
    }
}
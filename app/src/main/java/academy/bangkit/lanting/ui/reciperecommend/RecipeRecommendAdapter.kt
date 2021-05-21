package academy.bangkit.lanting.ui.reciperecommend

import academy.bangkit.lanting.data.model.Recipe
import academy.bangkit.lanting.databinding.ItemRecipeRowBinding
import academy.bangkit.lanting.utils.setImageFromUrl
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecipeRecommendAdapter: RecyclerView.Adapter<RecipeRecommendAdapter.RecipeRecommendViewHolder>() {
    val recipes = ArrayList<Recipe>()

    fun clearRecipe() {
        this.recipes.clear()
        notifyDataSetChanged()
    }

    fun setRecipes(recipes: List<Recipe>) {
        clearRecipe()
        this.recipes.addAll(recipes)
        notifyDataSetChanged()
    }

    inner class RecipeRecommendViewHolder(private val binding: ItemRecipeRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            with(binding) {
                val price = "Rp${recipe.price}"
                tvTitle.text = recipe.name
                tvDesc.text = price
                imgThumbnail.setImageFromUrl(recipe.imageUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeRecommendViewHolder {
        val binding =
            ItemRecipeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeRecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeRecommendViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size
}
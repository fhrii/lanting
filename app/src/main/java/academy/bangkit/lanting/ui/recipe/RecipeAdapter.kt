package academy.bangkit.lanting.ui.recipe

import academy.bangkit.lanting.ui.recipebudget.RecipeBudgetFragment
import academy.bangkit.lanting.ui.reciperecomend.RecipeRecomendFragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecipeAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RecipeBudgetFragment()
            1 -> RecipeRecomendFragment()
            else -> Fragment()
        }
    }

}
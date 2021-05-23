package academy.bangkit.lanting.ui.task

import academy.bangkit.lanting.data.model.Nutrition
import academy.bangkit.lanting.data.model.Profile
import academy.bangkit.lanting.ui.tasknutrients.TaskNutrientsFragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.*

class TaskAdapter(
    activity: AppCompatActivity,
    private val nutrients: List<Nutrition>,
    private val days: List<Date>
) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = days.size

    override fun createFragment(position: Int): Fragment {
        val taskNutrientsFragment = TaskNutrientsFragment()
        taskNutrientsFragment.nutrients = nutrients.filter { it.date == days[position] }
        taskNutrientsFragment.leftArrowActive = position != 0
        taskNutrientsFragment.rightArrowActive = position != days.size - 1
        return taskNutrientsFragment
    }

}
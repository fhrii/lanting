package academy.bangkit.lanting.ui.task

import academy.bangkit.lanting.data.model.Nutrition
import academy.bangkit.lanting.ui.tasknutrients.TaskNutrientsFragment
import academy.bangkit.lanting.utils.DateHelper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.*

class TaskAdapter(activity: AppCompatActivity, private val nutrients: List<Nutrition>) :
    FragmentStateAdapter(activity) {
    private val days = nutrients.map { it.date }.toSet().toMutableList()

    companion object {
        private const val TAG = "TaskAdapter"
    }

    override fun getItemCount(): Int {
        val today = DateHelper.todayTimeStamp()
        val theDate = days.find { it == today }

        if (theDate == null) days.add(today as Date)

        return days.size
    }

    override fun createFragment(position: Int): Fragment {
        val taskNutrientsFragment = TaskNutrientsFragment()
        taskNutrientsFragment.nutrients =  nutrients.filter { it.date == days[position] }
        return taskNutrientsFragment
    }

}
package academy.bangkit.lanting.ui.tasknutrients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.bangkit.lanting.R
import academy.bangkit.lanting.data.model.Nutrition
import android.util.Log

class TaskNutrientsFragment : Fragment() {
    lateinit var nutrients: List<Nutrition>

    companion object {
        const val EXTRA_NUTRIENTS = "extra_nutrients"
        private const val TAG = "TaskNutrientsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: $nutrients")
        return inflater.inflate(R.layout.fragment_task_nutrients, container, false)
    }
}
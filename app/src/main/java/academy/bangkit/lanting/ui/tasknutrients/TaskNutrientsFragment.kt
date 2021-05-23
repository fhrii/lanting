package academy.bangkit.lanting.ui.tasknutrients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.bangkit.lanting.data.model.Nutrition
import academy.bangkit.lanting.databinding.FragmentTaskNutrientsBinding
import academy.bangkit.lanting.utils.DateHelper
import academy.bangkit.lanting.utils.setVisible
import android.util.Log

class TaskNutrientsFragment : Fragment() {
    lateinit var nutrients: List<Nutrition>
    var leftArrowActive: Boolean = false
    var rightArrowActive: Boolean = false

    private lateinit var binding: FragmentTaskNutrientsBinding
    private var currentEnergy = 0
    private var currentFat = 0.0
    private var currentProtein = 0.0
    private var currentCarbohydrate = 0.0

    companion object {
        const val EXTRA_NUTRIENTS = "extra_nutrients"
        private const val TAG = "TaskNutrientsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskNutrientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDate()
        setNutrients()
        Log.d(TAG, "onViewCreated: $currentEnergy $currentFat $currentProtein $currentCarbohydrate")
    }

    private fun setDate() {
        binding.ivArrowLeft.setVisible(leftArrowActive)
        binding.ivArrowRight.setVisible(rightArrowActive)

        if (nutrients.isNotEmpty()) {
            val nutritionDate = nutrients[0].date
            val today = DateHelper.todayTimeStamp()
            if (nutritionDate != today) {
                binding.tvDate.text = DateHelper.formatDateToString(nutritionDate)
            }
        }
    }

    private fun setNutrients() {
        nutrients.forEach {
            currentEnergy += it.energy
            currentFat += it.fat
            currentProtein += it.protein
            currentCarbohydrate += it.carbohydrate
        }
    }
}
package academy.bangkit.lanting.ui.tasknutrients

import academy.bangkit.lanting.R
import academy.bangkit.lanting.data.ProfilePreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.bangkit.lanting.data.model.Nutrition
import academy.bangkit.lanting.databinding.FragmentTaskNutrientsBinding
import academy.bangkit.lanting.utils.DateHelper
import academy.bangkit.lanting.utils.ProfileNutrition
import academy.bangkit.lanting.utils.setVisible
import androidx.annotation.StringRes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskNutrientsFragment : Fragment() {
    lateinit var nutrients: List<Nutrition>
    var leftArrowActive: Boolean = false
    var rightArrowActive: Boolean = false

    @Inject
    lateinit var profilePreferences: ProfilePreferences

    private lateinit var binding: FragmentTaskNutrientsBinding
    private var currentEnergy = 0
    private var neededEnergy = 0
    private var currentFat = 0.0
    private var neededFat = 0.0
    private var currentProtein = 0.0
    private var neededProtein = 0.0
    private var currentCarbohydrate = 0.0
    private var neededCarbohydrate = 0.0

    companion object {
        @StringRes
        private const val ZERO = R.string._0
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
        fillNutrients()
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

        profilePreferences.profile?.let {
            val neededNutrition = ProfileNutrition.getNeededProfileNutrition(it)
            neededEnergy = neededNutrition.energy
            neededProtein = neededNutrition.protein
            neededFat = neededNutrition.fat
            neededCarbohydrate = neededNutrition.carbohydrate
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

    private fun fillNutrients() {
        binding.tvEnergyTotal.text = calcAndConvertNutrition(currentEnergy)
        binding.tvEnergyGoal.text = calcAndConvertNutrition(neededEnergy)
        binding.tvEnergyLeft.text =
            if (neededEnergy > currentEnergy)
                withNutritionUnit(calcAndConvertNutrition(neededEnergy - currentEnergy))
            else withNutritionUnit(getString(ZERO))
        binding.pbEnergy.progress = getProgress(currentEnergy.toDouble(), neededEnergy.toDouble())

        binding.tvProteinTotal.text = calcAndConvertNutrition(currentProtein)
        binding.tvProteinGoal.text = calcAndConvertNutrition(neededProtein)
        binding.tvProteinLeft.text =
            if (neededProtein > currentProtein)
                withNutritionUnit(calcAndConvertNutrition(neededProtein - currentProtein))
            else withNutritionUnit(getString(ZERO))
        binding.pbProtein.progress = getProgress(currentProtein, neededProtein)

        binding.tvFatTotal.text = calcAndConvertNutrition(currentFat)
        binding.tvFatGoal.text = calcAndConvertNutrition(neededFat)
        binding.tvFatLeft.text =
            if (neededFat > currentFat)
                withNutritionUnit(calcAndConvertNutrition(neededFat - currentFat))
            else withNutritionUnit(getString(ZERO))
        binding.pbFat.progress = getProgress(currentFat, neededFat)

        binding.tvCarbohydrateTotal.text = calcAndConvertNutrition(currentCarbohydrate)
        binding.tvCarbohydrateGoal.text = calcAndConvertNutrition(neededCarbohydrate)
        binding.tvCarbohydrateLeft.text =
            if (neededCarbohydrate > currentCarbohydrate)
                withNutritionUnit(calcAndConvertNutrition(neededCarbohydrate - currentCarbohydrate))
            else withNutritionUnit(getString(ZERO))
        binding.pbCarbohydrate.progress = getProgress(currentCarbohydrate, neededCarbohydrate)
    }

    private fun <T> calcAndConvertNutrition(value: T): String {
        return when (value) {
            is Int -> value.toString()
            is Double -> {
                lateinit var newValue: String
                if (value > 0.0) {
                    newValue = String.format("%.1f", value)
                    if (value % 1 == 0.0) newValue = value.toInt().toString()
                } else newValue = getString(ZERO)
                newValue
            }
            else -> getString(ZERO)
        }
    }

    private fun getProgress(currentValue: Double, toValue: Double): Int {
        val progress = ((currentValue / toValue) * 100.0).toInt()
        return if (progress > 100) 100 else progress
    }

    private fun withNutritionUnit(value: String): String {
        return getString(R.string.nutrition_unit, value)
    }
}
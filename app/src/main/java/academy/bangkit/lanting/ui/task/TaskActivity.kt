package academy.bangkit.lanting.ui.task

import academy.bangkit.lanting.data.ProfilePreferences
import academy.bangkit.lanting.databinding.ActivityTaskBinding
import academy.bangkit.lanting.utils.DateHelper
import academy.bangkit.lanting.utils.ResultState
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    private val viewModel: TaskViewModel by viewModels()

    @Inject
    lateinit var profilePreferences: ProfilePreferences

    companion object {
        private const val TAG = "TaskActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profilePreferences.profile?.let { profile ->
            viewModel.getNutritions(profile.id).observe(this@TaskActivity) { result ->
                when (result) {
                    is ResultState.Success -> {
                        val nutrients = result.data.sortedBy { it.date }
                        val days = nutrients.map { it.date }.toSet().toMutableList()
                        val today = DateHelper.todayTimeStamp()
                        val theDate = days.find { it == today }

                        if (theDate == null) days.add(today)

                        binding.vpTask.adapter = TaskAdapter(this@TaskActivity, nutrients, days)
                        binding.vpTask.currentItem = days.size - 1
                    }
                    is ResultState.Error -> {
                        Log.d(TAG, "onCreate: ${result.exception}")
                    }
                    is ResultState.Loading -> {
                        Log.d(TAG, "onCreate: Loading")
                    }
                }
            }
        }
    }
}
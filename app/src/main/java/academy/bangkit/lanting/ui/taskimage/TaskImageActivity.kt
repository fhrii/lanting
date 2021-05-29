package academy.bangkit.lanting.ui.taskimage

import academy.bangkit.lanting.R
import academy.bangkit.lanting.data.model.Food
import academy.bangkit.lanting.data.model.FoodSize
import academy.bangkit.lanting.data.model.Nutrition
import academy.bangkit.lanting.databinding.ActivityTaskImageBinding
import academy.bangkit.lanting.utils.DateHelper
import academy.bangkit.lanting.utils.ImageStorageManager
import academy.bangkit.lanting.utils.ResultState
import academy.bangkit.lanting.utils.setVisible
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskImageBinding
    private val viewModel: TaskImageViewModel by viewModels()
    private lateinit var taskImageAdapter: TaskImageAdapter

    private var foodImage: String? = null
    private var image: Bitmap? = null
    private val foods = mutableMapOf<Int, Nutrition>()

    companion object {
        private const val TAG = "TaskImageActivity"

        @StringRes
        private const val TEXT_FAILED = R.string.add_image_failed
        private const val TEXT_FAILED_EMPTY = R.string.add_image_failed_no_food
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_NUTRIENTS = "extra_nutrients"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setLayout()

        image?.also {
            viewModel.getFoods(it).observe(this) { result ->
                when (result) {
                    is ResultState.Success -> {
                        if (result.data.isEmpty()) {
                            createToast(getString(TEXT_FAILED_EMPTY))
                            setResultAndFinish(RESULT_CANCELED)
                            return@observe
                        }

                        result.data.forEach { food ->
                            val neededNutrition = food.size[0]
                                .let { foodSize->
                                    Nutrition(
                                        0,
                                        0,
                                        DateHelper.todayTimeStamp(),
                                        food.name,
                                        foodSize.value,
                                        foodSize.energy,
                                        foodSize.protein,
                                        foodSize.fat,
                                        foodSize.carbohydrate
                                    )
                                }
                            foods[food.id] = neededNutrition
                        }
                        taskImageAdapter.setFoods(result.data)
                        setLoading(false)
                    }
                    is ResultState.Error -> {
                        createToast(getString(TEXT_FAILED))
                        setResultAndFinish(RESULT_CANCELED)
                    }
                    is ResultState.Loading -> {
                        Log.d(TAG, "onCreate: Loading")
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        setResultAndFinish(RESULT_CANCELED)
        super.onBackPressed()
    }

    private fun setResultAndFinish(result: Int, nutrients: List<Nutrition>? = null) {
        val mIntent = Intent()
        mIntent.putExtra(EXTRA_IMAGE, foodImage)
        nutrients?.also {
            val newValue = ArrayList<Nutrition>()
            newValue.addAll(it)
            mIntent.putParcelableArrayListExtra(EXTRA_NUTRIENTS, newValue)
        }
        setResult(result, mIntent)
        finish()
    }

    private fun createToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setLoading(state: Boolean = true) {
        with(binding) {
            btnAddTask.setVisible(!state)
            rvTaskContainer.setVisible(!state)
            rvTask.setVisible(!state)
            pbLoading.setVisible(state)
        }
    }

    private fun setLayout() {
        binding = ActivityTaskImageBinding.inflate(layoutInflater)
        taskImageAdapter = TaskImageAdapter()

        foodImage = intent.getStringExtra(EXTRA_IMAGE)

        with(binding) {
            setContentView(binding.root)

            setLoading()

            foodImage?.let {
                image =
                    ImageStorageManager.getImageFromInternalStorage(this@TaskImageActivity, it)
                image?.let { bitmap -> ivFoodImage.setImageBitmap(bitmap) }
            }

            btnAddTask.setOnClickListener {
                val nutrients = foods.map { it.value }
                setResultAndFinish(RESULT_OK, nutrients)
            }

            taskImageAdapter.onSizeUpdated = object : TaskImageAdapter.OnSizeUpdated {
                override fun setOnSizeUpdated(food: Food, foodSize: FoodSize) {
                    val newNutrition = Nutrition(
                        0,
                        0,
                        DateHelper.todayTimeStamp(),
                        food.name,
                        foodSize.value,
                        foodSize.energy,
                        foodSize.protein,
                        foodSize.fat,
                        foodSize.carbohydrate
                    )
                    foods.remove(food.id)
                    foods[food.id] = newNutrition
                }
            }

            rvTask.adapter = taskImageAdapter
            rvTask.layoutManager = LinearLayoutManager(this@TaskImageActivity)
        }
    }
}
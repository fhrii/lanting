package academy.bangkit.lanting.ui.taskimage

import academy.bangkit.lanting.databinding.ActivityTaskImageBinding
import academy.bangkit.lanting.utils.ImageStorageManager
import academy.bangkit.lanting.utils.ResultState
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskImageBinding
    private val viewModel: TaskImageViewModel by viewModels()
    private lateinit var taskAdapter: TaskImageAdapter

    private var foodImage: String? = null

    companion object {
        private const val TAG = "TaskImageActivity"
        const val EXTRA_IMAGE = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setLayout()

        viewModel.getFoods().observe(this) { result ->
            when (result) {
                is ResultState.Success -> {
                    Log.d(TAG, "onCreate: ${result.data}")
                    taskAdapter.setFoods(result.data)
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

    private fun setLayout() {
        binding = ActivityTaskImageBinding.inflate(layoutInflater)
        taskAdapter = TaskImageAdapter()

        foodImage = intent.getStringExtra(EXTRA_IMAGE)

        with(binding) {
            setContentView(binding.root)

            btnBack.setOnClickListener {
                onBackPressed()
            }

            foodImage?.let {
                val image =
                    ImageStorageManager.getImageFromInternalStorage(this@TaskImageActivity, it)
                image?.let { bitmap -> ivFoodImage.setImageBitmap(bitmap) }
            }

            rvTask.adapter = taskAdapter
            rvTask.layoutManager = LinearLayoutManager(this@TaskImageActivity)
        }
    }

    override fun onBackPressed() {
        val mIntent = Intent()
        mIntent.putExtra(EXTRA_IMAGE, foodImage)
        setResult(RESULT_CANCELED, mIntent)
        finish()
        super.onBackPressed()
    }
}
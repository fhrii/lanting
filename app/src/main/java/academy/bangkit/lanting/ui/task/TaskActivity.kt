package academy.bangkit.lanting.ui.task

import academy.bangkit.lanting.R
import academy.bangkit.lanting.data.ProfilePreferences
import academy.bangkit.lanting.databinding.ActivityTaskBinding
import academy.bangkit.lanting.ui.taskimage.TaskImageActivity
import academy.bangkit.lanting.utils.DateHelper
import academy.bangkit.lanting.utils.ImageStorageManager
import academy.bangkit.lanting.utils.ResultState
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
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

        setNutrients()
        setImageButton()
        setBackButton()
    }

    private fun setNutrients() {
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

    private fun setImageButton() {
        val taskImageIntent = Intent(this, TaskImageActivity::class.java)
        var imageUriCamera: Uri? = null

        val taskImageLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                result?.apply {
                    when (resultCode) {
                        RESULT_OK -> Log.d(TAG, "setImageButton: OKE MANTAP")
                        RESULT_CANCELED -> {
                            val imageName =
                                data?.extras?.get(TaskImageActivity.EXTRA_IMAGE) as String?
                            imageName?.let {
                                ImageStorageManager
                                    .deleteImageFromInternalStorage(this@TaskActivity, it)
                            }
                        }
                    }
                }
            }

        val galleryLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                result?.apply {
                    if (resultCode == RESULT_OK) {
                        val imageUri = data?.data as Uri
                        val imageStream = contentResolver.openInputStream(imageUri)
                        val image = BitmapFactory.decodeStream(imageStream)
                        val imageName =
                            ImageStorageManager.saveToInternalStorage(this@TaskActivity, image)
                        taskImageIntent.putExtra(TaskImageActivity.EXTRA_IMAGE, imageName)
                        taskImageLauncher.launch(taskImageIntent)
                    }
                }
            }

        val cameraLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                result?.apply {
                    if (resultCode == RESULT_OK) {
                        imageUriCamera?.also {
                            val imageStream = contentResolver.openInputStream(it)
                            val image = BitmapFactory.decodeStream(imageStream)
                            val imageName =
                                ImageStorageManager.saveToInternalStorage(this@TaskActivity, image)
                            taskImageIntent.putExtra(TaskImageActivity.EXTRA_IMAGE, imageName)
                            taskImageLauncher.launch(taskImageIntent)
                        }
                    }
                }
            }

        binding.fab.setOnClickListener {
            val options = listOf(
                getString(R.string.choose_photo_camera),
                getString(R.string.choose_photo_gallery)
            ).toTypedArray()
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.choose_photo))
            builder.setItems(options) { _, item ->
                if (options[item] == options[0]) {
                    val values = ContentValues()
                    imageUriCamera =
                        contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                    val mIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    mIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriCamera)
                    Log.d(TAG, "setImageButton: $imageUriCamera")
                    cameraLauncher.launch(mIntent)
                } else {
                    val mIntent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    galleryLauncher.launch(mIntent)
                }
            }
            builder.show()
        }
    }

    private fun setBackButton() {
        binding.btnBack.setOnClickListener { finish() }
    }
}
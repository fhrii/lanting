package academy.bangkit.lanting.ui.profilenew

import academy.bangkit.lanting.R
import academy.bangkit.lanting.data.model.Profile
import academy.bangkit.lanting.databinding.ActivityProfileNewBinding
import academy.bangkit.lanting.ui.profiles.ProfilesActivity
import academy.bangkit.lanting.utils.DateHelper
import academy.bangkit.lanting.utils.ImageStorageManager
import academy.bangkit.lanting.utils.ResultState
import academy.bangkit.lanting.utils.setOnChangeListener
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ProfileNewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileNewBinding
    private val viewModel: ProfileNewViewModel by viewModels()

    private lateinit var myCalendar: Calendar
    private lateinit var datePicker: DatePickerDialog.OnDateSetListener

    private lateinit var type: String
    private var profileCategory: String? = null
    private var profile: Profile? = null

    private val TAG = "ProfileNewActivity"

    companion object {
        const val EXTRA_TYPE = "extra_type"
        const val TYPE_ADD = "type_add"
        const val TYPE_EDIT = "type_edit"
        const val EXTRA_PROFILE = "extra_profile"
        const val EXTRA_CATEGORY = "extra_category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCalendar = Calendar.getInstance()
        datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val date = DateHelper.formatDateOfBirthToString(myCalendar.time)
            viewModel.setDateOfBirth(date)
        }

        type = intent.getStringExtra(EXTRA_TYPE) as String
        profile = intent.getParcelableExtra(EXTRA_PROFILE)
        profileCategory = intent.getStringExtra(EXTRA_CATEGORY)

        setDataFormListener()
        setObservers()

        if (type == TYPE_EDIT) {
            with(binding) {
                btnCreate.setText(R.string.save)
                btnDelete.visibility = View.VISIBLE

                profile?.also { theProfile ->
                    viewModel.setName(theProfile.name)
                    viewModel.setDateOfBirth(theProfile.dateOfBirth)
                    viewModel.setHeight(theProfile.height)
                    viewModel.setWeight(theProfile.weight)
                    theProfile.allergy?.also { viewModel.setAllergy(it) }

                    theProfile.picture?.also { name ->
                        ImageStorageManager.getImageFromInternalStorage(
                            this@ProfileNewActivity,
                            name
                        )
                            ?.also { image ->
                                binding.civProfile.setImageBitmap(image)
                            }
                    }
                }
            }
        } else profileCategory = intent.getStringExtra(EXTRA_CATEGORY)
    }

    private fun showDatePickerDialog() {
        val dialog = DatePickerDialog(
            this@ProfileNewActivity,
            datePicker,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.datePicker.maxDate = System.currentTimeMillis() - 1000
        dialog.show()
    }

    private fun setDataFormListener() {
        val galleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            result?.apply {
                if (resultCode == RESULT_OK) {
                    val imageUri = data?.data as Uri
                    val imageStream = contentResolver.openInputStream(imageUri)
                    val image = BitmapFactory.decodeStream(imageStream)
                    viewModel.setPicture(image)
                }
            }
        }

        val cameraLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            result?.apply {
                if (resultCode == RESULT_OK) {
                    val image = data?.extras?.get("data") as Bitmap
                    viewModel.setPicture(image)
                }
            }
        }

        with(binding) {
            btnChangeProfilePhoto.setOnClickListener {
                val options = listOf(
                    getString(R.string.choose_photo_camera),
                    getString(R.string.choose_photo_gallery)
                ).toTypedArray()
                val builder = AlertDialog.Builder(this@ProfileNewActivity)
                builder.setTitle(getString(R.string.choose_photo))
                builder.setItems(options) { _, item ->
                    if (options[item] == options[0]) {
                        val mIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        cameraLauncher.launch(mIntent)
                    } else {
                        val mIntent =
                            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        galleryLauncher.launch(mIntent)
                    }
                }
                builder.show()
            }

            edtDate.setOnClickListener {
                showDatePickerDialog()
            }

            edtName.setOnChangeListener {
                if (!it.isNullOrEmpty()) {
                    viewModel.setName(it)
                }
            }

            edtDate.setOnChangeListener {
                if (!it.isNullOrEmpty()) {
                    viewModel.setDateOfBirth(it)
                }
            }

            edtHeight.setOnChangeListener {
                if (!it.isNullOrEmpty()) {
                    viewModel.setHeight(it.toInt())
                }
            }

            edtWeight.setOnChangeListener {
                if (!it.isNullOrEmpty()) {
                    viewModel.setWeight(it.toInt())
                }
            }

            edtAllergy.setOnChangeListener {
                if (!it.isNullOrEmpty()) {
                    viewModel.setAllergy(it)
                }
            }

            btnDelete.setOnClickListener { btnView ->
                profile?.also { thisProfile ->
                    viewModel.deleteProfile(thisProfile) {
                        when (it) {
                            is ResultState.Success<Boolean> -> {
                                finish()
                            }
                            is ResultState.Error -> {
                                btnView.isEnabled = true
                                Log.d(TAG, "setDataFormListener: ${it.exception}")
                            }
                            is ResultState.Loading -> {
                                btnView.isEnabled = false
                                Log.d(TAG, "setDataFormListener: Loading")
                            }
                        }
                    }
                }
            }

            btnCreate.setOnClickListener {
                val inputName = edtName.text.toString().trim()
                val inputDate = edtDate.text.toString().trim()
                val inputHeight = edtHeight.text.toString().trim()
                val inputWeight = edtWeight.text.toString().trim()
                val inputAllergy = edtAllergy.text.toString().trim()

                var areFieldsEmpty = false

                if (inputName.isEmpty()) {
                    areFieldsEmpty = true
                    edtName.error = getString(R.string.field_error)
                }

                if (inputDate.isEmpty()) {
                    areFieldsEmpty = true
                    edtDate.error = getString(R.string.field_error)
                }

                if (inputHeight.isEmpty() or (inputHeight == "0")) {
                    areFieldsEmpty = true
                    edtHeight.error = getString(R.string.field_error)
                }

                if (inputWeight.isEmpty() or (inputWeight == "0")) {
                    areFieldsEmpty = true
                    edtWeight.error = getString(R.string.field_error)
                }

                if (!areFieldsEmpty) {
                    if (type == TYPE_EDIT) {
                        profile?.also {
                            it.name = inputName
                            it.dateOfBirth
                            it.height = inputHeight.toInt()
                            it.weight = inputWeight.toInt()
                            it.allergy = if (inputAllergy.isEmpty()) null else inputAllergy

                            if (viewModel.picture.value != null) {
                                if (it.picture != null) {
                                    if (ImageStorageManager.deleteImageFromInternalStorage(
                                            this@ProfileNewActivity,
                                            it.picture!!
                                        )
                                    ) {
                                        it.picture = ImageStorageManager.saveToInternalStorage(
                                            this@ProfileNewActivity,
                                            viewModel.picture.value!!
                                        )
                                    }
                                } else {
                                    it.picture = ImageStorageManager.saveToInternalStorage(
                                        this@ProfileNewActivity,
                                        viewModel.picture.value!!
                                    )
                                }
                            }

                            viewModel.updateProfile(it) { result ->
                                when (result) {
                                    is ResultState.Success<Boolean> -> {
                                        Log.d(TAG, "setDataFormListener: Success")
                                        finish()
                                    }
                                    is ResultState.Error -> {
                                        binding.btnCreate.isEnabled = true
                                        Log.d(TAG, "setDataFormListener: Error")
                                    }
                                    is ResultState.Loading -> {
                                        binding.btnCreate.isEnabled = false
                                        Log.d(TAG, "setDataFormListener: Loading")
                                    }
                                }
                            }
                        }
                        return@setOnClickListener
                    }

                    val profile =
                        Profile(
                            0,
                            inputName,
                            inputDate,
                            inputHeight.toInt(),
                            inputWeight.toInt(),
                            if (inputAllergy.isEmpty()) null else inputAllergy,
                            profileCategory!!,
                            if (viewModel.picture.value != null) ImageStorageManager.saveToInternalStorage(
                                this@ProfileNewActivity,
                                viewModel.picture.value!!
                            ) else null
                        )

                    viewModel.insertProfile(profile) {
                        when (it) {
                            is ResultState.Success<Boolean> -> {
                                Log.d(TAG, "setDataFormListener: Success")
                                val mIntent =
                                    Intent(this@ProfileNewActivity, ProfilesActivity::class.java)
                                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(mIntent)
                            }
                            is ResultState.Error -> {
                                binding.btnCreate.isEnabled = true
                                Log.d(TAG, "setDataFormListener: Error")
                            }
                            is ResultState.Loading -> {
                                binding.btnCreate.isEnabled = false
                                Log.d(TAG, "setDataFormListener: Loading")
                            }
                        }
                    }

                }
            }
        }
    }

    private fun setObservers() {
        viewModel.name.observe(this) {
            Log.d(TAG, "setObservers: $it")
            if (it != binding.edtName.text?.toString()) binding.edtName.setText(it)
        }

        viewModel.dateOfBirth.observe(this) {
            if (it != binding.edtDate.text?.toString()) binding.edtDate.setText(it)
        }

        viewModel.height.observe(this) {
            if (it.toString() != binding.edtHeight.text?.toString()) binding.edtHeight.setText(it.toString())
        }

        viewModel.weight.observe(this) {
            if (it.toString() != binding.edtWeight.text?.toString()) binding.edtWeight.setText(it.toString())
        }

        viewModel.allergy.observe(this) {
            if (it != binding.edtAllergy.text?.toString()) binding.edtAllergy.setText(it)
        }

        viewModel.picture.observe(this) {
            binding.civProfile.setImageBitmap(it)
        }
    }
}
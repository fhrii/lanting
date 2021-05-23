package academy.bangkit.lanting.ui.profilenew

import academy.bangkit.lanting.R
import academy.bangkit.lanting.data.model.Profile
import academy.bangkit.lanting.data.model.ProfileCategory
import academy.bangkit.lanting.databinding.ActivityProfileNewBinding
import academy.bangkit.lanting.ui.profiles.ProfilesActivity
import academy.bangkit.lanting.utils.*
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ProfileNewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileNewBinding
    private val viewModel: ProfileNewViewModel by viewModels()

    private lateinit var myCalendar: Calendar
    private lateinit var babyCalendar: Calendar
    private lateinit var datePicker: DatePickerDialog.OnDateSetListener
    private lateinit var babysDatePicker: DatePickerDialog.OnDateSetListener

    private lateinit var type: String
    private var profileCategory: ProfileCategory = ProfileCategory.IBU
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
            viewModel.setDateOfBirth(myCalendar.time)
        }

        babyCalendar = Calendar.getInstance()
        babysDatePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            babyCalendar.set(Calendar.YEAR, year)
            babyCalendar.set(Calendar.MONTH, month)
            babyCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            viewModel.setBabysBirthDate(babyCalendar.time)
        }

        type = intent.getStringExtra(EXTRA_TYPE) as String
        profile = intent.getParcelableExtra(EXTRA_PROFILE)

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

                    if (theProfile.category == ProfileCategory.BUSUI) {
                        profileCategory = ProfileCategory.BUSUI
                        theProfile.babysBirthDate?.let {
                            viewModel.setBabysBirthDate(it)
                        }
                    } else if (theProfile.category == ProfileCategory.BUMIL) {
                        profileCategory = ProfileCategory.BUMIL
                        theProfile.gestationalAge?.let {
                            viewModel.setGestationalAge(it)
                        }
                    }

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
        } else profileCategory = intent.getSerializableExtra(EXTRA_CATEGORY) as ProfileCategory

        setDataFormListener()
        setObservers()
    }

    private fun showDatePickerDialog(
        datePicker: DatePickerDialog.OnDateSetListener,
        calendar: Calendar
    ) {
        val dialog = DatePickerDialog(
            this@ProfileNewActivity,
            datePicker,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.datePicker.maxDate = System.currentTimeMillis() - 1000
        dialog.show()
    }

    private fun setDataFormListener() {
        if (profileCategory != ProfileCategory.BUMIL && profileCategory != ProfileCategory.BUSUI) {
            binding.edtExt.setVisible(false)
            binding.tvExt.setVisible(false)
        } else {
            if (profileCategory == ProfileCategory.BUMIL) {
                binding.tvExt.text = getString(R.string.gestational_age)
                binding.edtExt.isClickable = true
                binding.edtExt.isCursorVisible = true
                binding.edtExt.isFocusableInTouchMode = true
                binding.edtExt.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    binding.edtExt.focusable = EditText.FOCUSABLE
                }
            }
        }

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
                showDatePickerDialog(datePicker, myCalendar)
            }

            if (profileCategory == ProfileCategory.BUSUI) {
                edtExt.setOnClickListener {
                    showDatePickerDialog(babysDatePicker, babyCalendar)
                }
                edtExt.setOnChangeListener {
                    if (!it.isNullOrEmpty()) {
                        viewModel.setBabysBirthDate(DateHelper.formatStringToDate(it))
                    }
                }
            } else if (profileCategory == ProfileCategory.BUMIL) {
                edtExt.setOnChangeListener {
                    if (!it.isNullOrEmpty()) {
                        viewModel.setGestationalAge(it.toInt())
                    }
                }
            }

            edtName.setOnChangeListener {
                if (!it.isNullOrEmpty()) {
                    viewModel.setName(it)
                }
            }

            edtDate.setOnChangeListener {
                if (!it.isNullOrEmpty()) {
                    viewModel.setDateOfBirth(DateHelper.formatStringToDate(it))
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
                            is ResultState.Success -> {
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
                val inputExt = edtExt.text.toString().trim()

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

                if (profileCategory == ProfileCategory.BUMIL || profileCategory == ProfileCategory.BUSUI) {
                    if (inputExt.isEmpty()) {
                        areFieldsEmpty = true
                        edtExt.error = getString(R.string.field_error)
                    }
                }

                if (!areFieldsEmpty) {
                    if (type == TYPE_EDIT) {
                        profile?.also {
                            it.name = inputName
                            it.dateOfBirth = DateHelper.formatStringToDate(inputDate)
                            it.height = inputHeight.toInt()
                            it.weight = inputWeight.toInt()
                            it.allergy = if (inputAllergy.isEmpty()) null else inputAllergy
                            it.babysBirthDate = if (profileCategory == ProfileCategory.BUSUI)
                                DateHelper.formatStringToDate(inputExt) else null
                            it.gestationalAge =
                                if (profileCategory == ProfileCategory.BUMIL) inputExt.toInt() else null

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
                                    is ResultState.Success -> {
                                        Log.d(TAG, "setDataFormListener: Success")
                                        finish()
                                    }
                                    is ResultState.Error -> {
                                        binding.btnCreate.isEnabled = true
                                        Log.d(TAG, "setDataFormListener: ${result.exception}")
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
                            DateHelper.formatStringToDate(inputDate),
                            inputHeight.toInt(),
                            inputWeight.toInt(),
                            if (inputAllergy.isEmpty()) null else inputAllergy,
                            profileCategory,
                            if (profileCategory == ProfileCategory.BUSUI)
                                DateHelper.formatStringToDate(inputExt) else null,
                            if (profileCategory == ProfileCategory.BUMIL) inputExt.toInt() else null,
                            if (viewModel.picture.value != null) ImageStorageManager.saveToInternalStorage(
                                this@ProfileNewActivity,
                                viewModel.picture.value!!
                            ) else null
                        )

                    Log.d(TAG, "setDataFormListener: $profile")

                    viewModel.insertProfile(profile) {
                        when (it) {
                            is ResultState.Success -> {
                                Log.d(TAG, "setDataFormListener: Success")
                                val mIntent =
                                    Intent(this@ProfileNewActivity, ProfilesActivity::class.java)
                                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(mIntent)
                            }
                            is ResultState.Error -> {
                                binding.btnCreate.isEnabled = true
                                Log.d(TAG, "setDataFormListener: ${it.exception}")
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
            if (it != binding.edtName.text?.toString()) binding.edtName.setText(it)
        }

        viewModel.dateOfBirth.observe(this) {
            val dateOfBirth = DateHelper.formatDateToString(it)
            if (dateOfBirth != binding.edtDate.text?.toString())
                binding.edtDate.setText(dateOfBirth)
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

        if (profileCategory == ProfileCategory.BUMIL) {
            viewModel.gestationalAge.observe(this@ProfileNewActivity) { value ->
                value?.let {
                    if (it.toString() != binding.edtExt.text?.toString())
                        binding.edtExt.setText(it.toString())
                }
            }
        } else if (profileCategory == ProfileCategory.BUSUI) {
            viewModel.babysBirthDate.observe(this@ProfileNewActivity) { date ->
                date?.let {
                    val babysBirthDate = DateHelper.formatDateToString(it)
                    if (babysBirthDate != binding.edtExt.text?.toString())
                        binding.edtExt.setText(babysBirthDate)
                }
            }
        }
    }
}
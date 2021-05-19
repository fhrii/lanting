package academy.bangkit.lanting.ui.profilecategory

import academy.bangkit.lanting.R
import academy.bangkit.lanting.data.model.ProfileCategory
import academy.bangkit.lanting.databinding.ActivityProfileCategoryBinding
import academy.bangkit.lanting.ui.profilenew.ProfileNewActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat

class ProfileCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileCategoryBinding
    private val viewModel: ProfileCategoryViewModel by viewModels()
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectorListener()
        setObservers()
        setNextListeners()
    }

    private fun resetSelector() {
        with(binding) {
            btnBaduta.setBackgroundResource(R.drawable.bg_btn_green_dark)
            btnBumil.setBackgroundResource(R.drawable.bg_btn_green_dark)
            btnBusui.setBackgroundResource(R.drawable.bg_btn_green_dark)
            btnIbu.setBackgroundResource(R.drawable.bg_btn_green_dark)

            val color = ContextCompat.getColor(this@ProfileCategoryActivity, R.color.green_dark)
            tvBaduta.setTextColor(color)
            tvBumil.setTextColor(color)
            tvBusui.setTextColor(color)
            tvIbu.setTextColor(color)
        }
    }

    private fun selectorListener() {
        val color = ContextCompat.getColor(this@ProfileCategoryActivity, R.color.cream)

        with(binding) {
            btnBaduta.setOnClickListener {
                resetSelector()
                btnBaduta.setBackgroundResource(R.drawable.bg_btn_cream)
                tvBaduta.setTextColor(color)
                viewModel.setCategory(ProfileCategory.BADUTA)
            }
            btnBumil.setOnClickListener {
                resetSelector()
                btnBumil.setBackgroundResource(R.drawable.bg_btn_cream)
                tvBumil.setTextColor(color)
                viewModel.setCategory(ProfileCategory.BUMIL)
            }
            btnBusui.setOnClickListener {
                resetSelector()
                btnBusui.setBackgroundResource(R.drawable.bg_btn_cream)
                tvBusui.setTextColor(color)
                viewModel.setCategory(ProfileCategory.BUSUI)
            }
            btnIbu.setOnClickListener {
                resetSelector()
                btnIbu.setBackgroundResource(R.drawable.bg_btn_cream)
                tvIbu.setTextColor(color)
                viewModel.setCategory(ProfileCategory.IBU)
            }
        }
    }

    private fun setObservers() {
        with(viewModel) {
            category.observe(this@ProfileCategoryActivity) {
                this@ProfileCategoryActivity.category = it
            }
        }
    }

    private fun setNextListeners() {
        with(binding) {
            btnNext.setOnClickListener {
                Log.d("TAG", "setNextListeners: $category")
                if (category != null) {
                    val mIntent =
                        Intent(this@ProfileCategoryActivity, ProfileNewActivity::class.java)
                    mIntent.putExtra(ProfileNewActivity.EXTRA_TYPE, ProfileNewActivity.TYPE_ADD)
                    mIntent.putExtra(ProfileNewActivity.EXTRA_CATEGORY, category)
                    startActivity(mIntent)
                }
            }
        }
    }
}
package academy.bangkit.lanting.ui.profiles

import academy.bangkit.lanting.data.ProfilePreferences
import academy.bangkit.lanting.data.model.Profile
import academy.bangkit.lanting.databinding.ActivityProfilesBinding
import academy.bangkit.lanting.ui.home.HomeActivity
import academy.bangkit.lanting.ui.profilecategory.ProfileCategoryActivity
import academy.bangkit.lanting.ui.profilenew.ProfileNewActivity
import academy.bangkit.lanting.utils.ResultState
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfilesActivity : AppCompatActivity(), OnItemProfileClicked {
    private lateinit var binding: ActivityProfilesBinding
    private lateinit var profilesAdapter: ProfilesAdapter
    private val viewModel: ProfilesViewModel by viewModels()

    @Inject
    lateinit var profilePreferences: ProfilePreferences

    companion object {
        private const val TAG = "ProfilesActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profilesAdapter = ProfilesAdapter()

        with(binding.rvProfile) {
            layoutManager = LinearLayoutManager(this@ProfilesActivity)
            setHasFixedSize(true)
            adapter = profilesAdapter
        }

        with(binding) {
            btnAddProfile.setOnClickListener {
                val mIntent = Intent(this@ProfilesActivity, ProfileCategoryActivity::class.java)
                startActivity(mIntent)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getProfiles().observe(this) {
            when (it) {
                is ResultState.Success -> {
                    profilesAdapter.setProfiles(it.data)
                }
                is ResultState.Error -> {
                    Log.d(TAG, "onCreate: ${it.exception}")
                }
                is ResultState.Loading -> {
                    Log.d(TAG, "onCreate: Loading")
                }
            }
        }
    }

    override fun setOnItemEditClicked(profile: Profile) {
        val mIntent = Intent(this, ProfileNewActivity::class.java)
        mIntent.putExtra(ProfileNewActivity.EXTRA_TYPE, ProfileNewActivity.TYPE_EDIT)
        mIntent.putExtra(ProfileNewActivity.EXTRA_PROFILE, profile)
        startActivity(mIntent)
    }

    override fun setOnItemClicked(profile: Profile) {
        profilePreferences.setProfile(profile)
        val mIntent = Intent(this, HomeActivity::class.java)
        startActivity(mIntent)
        finish()
    }
}
package academy.bangkit.lanting.ui.getting

import academy.bangkit.lanting.databinding.ActivityGettingBinding
import academy.bangkit.lanting.ui.profiles.ProfilesActivity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager

class GettingActivity : AppCompatActivity(), OnBtnGettingStartedClicked {
    private lateinit var binding: ActivityGettingBinding
    private lateinit var myPreferences: SharedPreferences

    companion object {
        const val ALREADY_USED = "already_used"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.vpGettingStarted.adapter = sectionsPagerAdapter
        binding.ciIndicator.setViewPager(binding.vpGettingStarted)
        sectionsPagerAdapter.registerAdapterDataObserver(binding.ciIndicator.adapterDataObserver)

        myPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun setOnBtnGettingStartedClicked() {
        with(myPreferences.edit()) {
            putBoolean(ALREADY_USED, true)
            apply()
        }
        val mIntent = Intent(this, ProfilesActivity::class.java)
        startActivity(mIntent)
    }
}
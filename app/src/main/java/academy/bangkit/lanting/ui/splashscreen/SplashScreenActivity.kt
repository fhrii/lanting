package academy.bangkit.lanting.ui.splashscreen

import academy.bangkit.lanting.data.ProfilePreferences
import academy.bangkit.lanting.databinding.ActivitySplashScreenBinding
import academy.bangkit.lanting.ui.getting.GettingActivity
import academy.bangkit.lanting.ui.home.HomeActivity
import academy.bangkit.lanting.ui.profiles.ProfilesActivity
import academy.bangkit.lanting.utils.DateHelper
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var myPreferences: SharedPreferences

    @Inject
    lateinit var profilePrefences: ProfilePreferences

    companion object {
        private const val TIME_OUT = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val skipToProfile = myPreferences.getBoolean(GettingActivity.ALREADY_USED, false)

        Handler(Looper.getMainLooper()).postDelayed({
            lateinit var mIntent: Intent

            if (skipToProfile) {
                mIntent = Intent(this, ProfilesActivity::class.java)
                if (profilePrefences.profile != null)
                    mIntent = Intent(this, HomeActivity::class.java)
            } else mIntent = Intent(this, GettingActivity::class.java)

            startActivity(mIntent)
            finish()
        }, TIME_OUT)
    }
}
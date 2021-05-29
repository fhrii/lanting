package academy.bangkit.lanting.ui.home

import academy.bangkit.lanting.R
import academy.bangkit.lanting.data.ProfilePreferences
import academy.bangkit.lanting.data.model.Profile
import academy.bangkit.lanting.databinding.ActivityHomeBinding
import academy.bangkit.lanting.databinding.LayoutHomeBinding
import academy.bangkit.lanting.databinding.NavHeaderHomeBinding
import academy.bangkit.lanting.ui.article.ArticleActivity
import academy.bangkit.lanting.ui.profiles.ProfilesActivity
import academy.bangkit.lanting.ui.recipe.RecipeActivity
import academy.bangkit.lanting.ui.task.TaskActivity
import academy.bangkit.lanting.utils.DateHelper
import academy.bangkit.lanting.utils.ImageStorageManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeBinding: LayoutHomeBinding
    private lateinit var navHeaderHomeBinding: NavHeaderHomeBinding
    private lateinit var profile: Profile

    @Inject
    lateinit var profilePreferences: ProfilePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        setupHeaderDrawer()
        setupHomeLayout()
    }

    private fun init() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        navHeaderHomeBinding = NavHeaderHomeBinding.bind(binding.navView.getHeaderView(0))

        if (profilePreferences.profile == null) {
            val mIntent = Intent(this, ProfilesActivity::class.java)
            startActivity(mIntent)
            finish()
        }

        profile = profilePreferences.profile as Profile
        setContentView(binding.root)
    }

    fun setupHeaderDrawer() {
        binding.navView.setNavigationItemSelectedListener(this)
        navHeaderHomeBinding.tvProfile.text = profile.name
        profile.picture?.also { fileName ->
            ImageStorageManager.getImageFromInternalStorage(this, fileName).also { image ->
                navHeaderHomeBinding.civProfile.setImageBitmap(image)
            }
        }
    }

    fun setupHomeLayout() {
        homeBinding = LayoutHomeBinding.bind(binding.layoutHome.root)
        with(homeBinding) {
            btnNav.setOnClickListener {
                binding.drawerLayout.openDrawer(Gravity.LEFT)
            }
            btnRecipe.setOnClickListener {
                val mIntent = Intent(this@HomeActivity, RecipeActivity::class.java)
                startActivity(mIntent)
            }
            btnArticle.setOnClickListener {
                val mIntent = Intent(this@HomeActivity, ArticleActivity::class.java)
                startActivity(mIntent)
            }
            btnTask.setOnClickListener {
                val mIntent = Intent(this@HomeActivity, TaskActivity::class.java)
                startActivity(mIntent)
            }
            btnLearnMore.setOnClickListener {
                val mIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://republika.co.id/berita//qr98iw456/studi-satu-dari-tiga-bayi-indonesia-terdiagnosa-stunting")
                )
                startActivity(mIntent)
            }

            tvHello.text = getString(R.string.hello_user, profile.name)
            tvDate.text = DateHelper.today()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_change_profile -> {
                val mIntent = Intent(this, ProfilesActivity::class.java)
                startActivity(mIntent)
                finish()
            }
        }
        return false
    }

}
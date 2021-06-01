package academy.bangkit.lanting.ui.about

import academy.bangkit.lanting.databinding.ActivityAboutBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }
        binding.rvAbout.layoutManager = LinearLayoutManager(this)
        binding.rvAbout.setHasFixedSize(true)
        binding.rvAbout.adapter = AboutAdapter()
    }
}
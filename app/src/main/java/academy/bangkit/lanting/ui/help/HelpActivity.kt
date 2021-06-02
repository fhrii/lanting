package academy.bangkit.lanting.ui.help

import academy.bangkit.lanting.databinding.ActivityHelpBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HelpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
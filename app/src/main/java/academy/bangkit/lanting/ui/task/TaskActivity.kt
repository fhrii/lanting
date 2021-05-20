package academy.bangkit.lanting.ui.task

import academy.bangkit.lanting.databinding.ActivityTaskBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
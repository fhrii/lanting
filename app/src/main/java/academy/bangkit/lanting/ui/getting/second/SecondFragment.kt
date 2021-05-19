package academy.bangkit.lanting.ui.getting.second

import academy.bangkit.lanting.databinding.FragmentSecondBinding
import academy.bangkit.lanting.ui.getting.OnBtnGettingStartedClicked
import academy.bangkit.lanting.ui.profilenew.ProfileNewActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.btnGetStarted.setOnClickListener {
//            val mIntent = Intent(this.context, ProfileNewActivity::class.java)
//            startActivity(mIntent)

            (activity as OnBtnGettingStartedClicked).setOnBtnGettingStartedClicked()
        }
        return binding.root
    }

}
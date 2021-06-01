package academy.bangkit.lanting.ui.about

import academy.bangkit.lanting.data.model.AboutProfile
import academy.bangkit.lanting.databinding.ItemAboutRowBinding
import academy.bangkit.lanting.utils.DummyData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AboutAdapter : RecyclerView.Adapter<AboutAdapter.AboutViewHolder>() {
    private val profiles = DummyData.getAboutProfiles()

    inner class AboutViewHolder(private val binding: ItemAboutRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: AboutProfile) {
            with(binding) {
                tvAboutName.text = profile.name
                tvAboutUniv.text = profile.univ
                tvAboutPath.text = profile.path
                ivAboutPicture.setImageResource(profile.photo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutViewHolder {
        val binding =
            ItemAboutRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AboutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AboutViewHolder, position: Int) {
        holder.bind(profiles[position])
    }

    override fun getItemCount(): Int = profiles.size
}
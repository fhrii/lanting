package academy.bangkit.lanting.ui.profiles

import academy.bangkit.lanting.data.model.Profile
import academy.bangkit.lanting.databinding.ItemProfileRowBinding
import academy.bangkit.lanting.utils.ImageStorageManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProfilesAdapter : RecyclerView.Adapter<ProfilesAdapter.ProfilesViewHolder>() {
    private val profiles = ArrayList<Profile>()

    fun setProfiles(profiles: List<Profile>) {
        this.profiles.clear()
        this.profiles.addAll(profiles)
        notifyDataSetChanged()
    }

    inner class ProfilesViewHolder(private val binding: ItemProfileRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            with(binding) {
                binding.tvProfileName.text = profile.name
                binding.tvProfileCategory.text = profile.category

                profile.picture?.also {
                    val image =
                        ImageStorageManager.getImageFromInternalStorage(itemView.context, it)
                    image?.also { civProfile.setImageBitmap(image) }
                }

                btnEdit.setOnClickListener {
                    (itemView.context as OnItemProfileClicked).setOnItemEditClicked(profile)
                }

                itemView.setOnClickListener {
                    (itemView.context as OnItemProfileClicked).setOnItemClicked(profile)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesViewHolder {
        val binding =
            ItemProfileRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfilesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfilesViewHolder, position: Int) {
        holder.bind(profiles[position])
    }

    override fun getItemCount(): Int = profiles.size
}
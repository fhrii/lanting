package academy.bangkit.lanting.ui.taskimage

import academy.bangkit.lanting.data.model.FoodSize
import academy.bangkit.lanting.databinding.ItemTaskSizeColumnBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView

class TaskImageSizeAdapter : RecyclerView.Adapter<TaskImageSizeAdapter.TaskImageSizeViewHolder>() {
    private val sizes = ArrayList<FoodSize>()
    var onItemClicked: OnItemClicked? = null

    fun clearSizes() {
        sizes.clear()
        notifyDataSetChanged()
    }

    fun setSizes(sizes: List<FoodSize>) {
        this.sizes.addAll(sizes)
        notifyDataSetChanged()
    }

    inner class TaskImageSizeViewHolder(private val binding: ItemTaskSizeColumnBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(size: FoodSize) {
            with(binding) {
                btnTaskSize.text = size.value
                if (adapterPosition == sizes.size - 1) itemView.updatePadding(right = 48)
                onItemClicked?.also { event ->
                    btnTaskSize.setOnClickListener { event.setOnItemClicked(size) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskImageSizeViewHolder {
        val binding =
            ItemTaskSizeColumnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskImageSizeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskImageSizeViewHolder, position: Int) {
        holder.bind(sizes[position])
    }

    override fun getItemCount(): Int = sizes.size

    interface OnItemClicked {
        fun setOnItemClicked(foodSize: FoodSize)
    }
}
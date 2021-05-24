package academy.bangkit.lanting.ui.taskimage

import academy.bangkit.lanting.data.model.Food
import academy.bangkit.lanting.databinding.ItemTaskRowBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TaskImageAdapter : RecyclerView.Adapter<TaskImageAdapter.TaskImageViewHolder>() {
    private val foods = ArrayList<Food>()

    fun clearFoods() {
        foods.clear()
        notifyDataSetChanged()
    }

    fun setFoods(foods: List<Food>) {
        this.foods.addAll(foods)
        notifyDataSetChanged()
    }

    inner class TaskImageViewHolder(private val binding: ItemTaskRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            with(binding) {
                tvFoodName.text = food.name
                tvFoodSize.text = food.size[0].value
                tvFoodEnergy.text = food.size[0].energy.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskImageViewHolder {
        val binding = ItemTaskRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskImageViewHolder, position: Int) {
        holder.bind(foods[position])
    }

    override fun getItemCount(): Int = foods.size
}
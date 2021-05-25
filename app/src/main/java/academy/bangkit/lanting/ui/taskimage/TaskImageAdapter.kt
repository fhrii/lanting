package academy.bangkit.lanting.ui.taskimage

import academy.bangkit.lanting.R
import academy.bangkit.lanting.data.model.Food
import academy.bangkit.lanting.data.model.FoodSize
import academy.bangkit.lanting.databinding.ItemTaskRowBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskImageAdapter : RecyclerView.Adapter<TaskImageAdapter.TaskImageViewHolder>() {
    private val foods = ArrayList<Food>()
    var onSizeUpdated: OnSizeUpdated? = null

    companion object {
        @StringRes
        private const val NUTRITION_WITH_KKAL = R.string.nutrition_unit_kkal
    }

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

                val adapter = TaskImageSizeAdapter()
                adapter.onItemClicked = object : TaskImageSizeAdapter.OnItemClicked {
                    override fun setOnItemClicked(foodSize: FoodSize) {
                        tvFoodSize.text = foodSize.value
                        tvFoodEnergy.text = itemView.context.getString(
                            NUTRITION_WITH_KKAL,
                            foodSize.energy.toString()
                        )
                        onSizeUpdated?.setOnSizeUpdated(food, foodSize)
                    }
                }
                adapter.setSizes(food.size)
                rvTaskButton.adapter = adapter
                rvTaskButton.layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
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

    interface OnSizeUpdated {
        fun setOnSizeUpdated(food: Food, foodSize: FoodSize)
    }
}
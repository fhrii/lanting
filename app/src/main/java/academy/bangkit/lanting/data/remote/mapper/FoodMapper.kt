package academy.bangkit.lanting.data.remote.mapper

import academy.bangkit.lanting.data.model.Food
import academy.bangkit.lanting.data.remote.response.FoodTaskResponse
import academy.bangkit.lanting.utils.EntityMapper
import javax.inject.Inject

class FoodMapper @Inject constructor(private val foodSizeMapper: FoodSizeMapper) :
    EntityMapper<FoodTaskResponse, Food> {
    override fun mapFromEntity(entity: FoodTaskResponse): Food {
        return Food(
            entity.name,
            foodSizeMapper.mapFromEntityList(entity.size)
        )
    }

    override fun mapToEntity(model: Food): FoodTaskResponse {
        return FoodTaskResponse(
            model.name,
            foodSizeMapper.mapToEntityList(model.size)
        )
    }

    fun mapFromEntityList(entities: List<FoodTaskResponse>): List<Food> {
        return entities.map { mapFromEntity(it) }
    }
}
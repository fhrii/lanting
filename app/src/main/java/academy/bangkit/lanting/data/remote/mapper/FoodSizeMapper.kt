package academy.bangkit.lanting.data.remote.mapper

import academy.bangkit.lanting.data.model.FoodSize
import academy.bangkit.lanting.data.remote.response.FoodSizeResponse
import academy.bangkit.lanting.utils.EntityMapper
import javax.inject.Inject

class FoodSizeMapper @Inject constructor() : EntityMapper<FoodSizeResponse, FoodSize> {
    override fun mapFromEntity(entity: FoodSizeResponse): FoodSize {
        return FoodSize(
            entity.value,
            entity.energy,
            entity.fat,
            entity.protein,
            entity.carbohydrate
        )
    }

    override fun mapToEntity(model: FoodSize): FoodSizeResponse {
        return FoodSizeResponse(
            model.value,
            model.energy,
            model.fat,
            model.protein,
            model.carbohydrate
        )
    }

    fun mapFromEntityList(entities: List<FoodSizeResponse>): List<FoodSize> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(entities: List<FoodSize>): List<FoodSizeResponse> {
        return entities.map { mapToEntity(it) }
    }
}
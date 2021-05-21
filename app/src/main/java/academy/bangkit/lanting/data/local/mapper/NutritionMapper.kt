package academy.bangkit.lanting.data.local.mapper

import academy.bangkit.lanting.data.local.entity.NutritionEntity
import academy.bangkit.lanting.data.model.Nutrition
import academy.bangkit.lanting.utils.EntityMapper
import javax.inject.Inject

class NutritionMapper @Inject constructor() : EntityMapper<NutritionEntity, Nutrition> {
    override fun mapFromEntity(entity: NutritionEntity): Nutrition {
        return Nutrition(
            entity.id,
            entity.profileId,
            entity.date,
            entity.name,
            entity.size,
            entity.energy,
            entity.fat,
            entity.protein,
            entity.carbohydrate
        )
    }

    override fun mapToEntity(model: Nutrition): NutritionEntity {
        return NutritionEntity(
            model.id,
            model.profileId,
            model.date,
            model.name,
            model.size,
            model.energy,
            model.fat,
            model.protein,
            model.carbohydrate
        )
    }

    fun mapFromEntityList(entities: List<NutritionEntity>): List<Nutrition> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntitiyList(models: List<Nutrition>): List<NutritionEntity> {
        return models.map { mapToEntity(it) }
    }
}
package academy.bangkit.lanting.data.remote.mapper

import academy.bangkit.lanting.data.local.entity.ProfileEntity
import academy.bangkit.lanting.data.model.Profile
import academy.bangkit.lanting.data.model.Recipe
import academy.bangkit.lanting.data.remote.response.RecipeResponse
import academy.bangkit.lanting.utils.EntityMapper
import javax.inject.Inject

class RecipeMapper @Inject constructor() : EntityMapper<RecipeResponse, Recipe> {
    override fun mapFromEntity(entity: RecipeResponse): Recipe {
        return Recipe(
            entity.name,
            entity.ingridients,
            entity.howToCook,
            entity.nutrients,
            entity.seasonings,
            entity.imageUrl,
            entity.price
        )
    }

    override fun mapToEntity(model: Recipe): RecipeResponse {
        return RecipeResponse(
            model.name,
            model.ingridients,
            model.howToCook,
            model.nutrients,
            model.seasonings,
            model.imageUrl,
            model.price
        )
    }

    fun mapFromEntityList(entities: List<RecipeResponse>): List<Recipe> {
        return entities.map { mapFromEntity(it) }
    }
}
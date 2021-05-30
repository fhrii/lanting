package academy.bangkit.lanting.data.remote.mapper

import academy.bangkit.lanting.data.model.ProfileCategory
import academy.bangkit.lanting.data.model.Recipe
import academy.bangkit.lanting.data.remote.response.RecipeResponse
import academy.bangkit.lanting.utils.EntityMapper
import javax.inject.Inject

class RecipeMapper @Inject constructor() : EntityMapper<RecipeResponse, Recipe> {
    override fun mapFromEntity(entity: RecipeResponse): Recipe {
        val category =
            if (entity.category == "baduta") ProfileCategory.BADUTA else ProfileCategory.IBU

        return Recipe(
            entity.name,
            entity.ingridients,
            entity.howToCook,
            entity.nutrients,
            entity.seasonings,
            entity.imageUrl,
            category,
            entity.price,
        )
    }

    override fun mapToEntity(model: Recipe): RecipeResponse {
        val category = if (model.category == ProfileCategory.BADUTA) "baduta" else "ibu"

        return RecipeResponse(
            model.name,
            model.ingredients,
            model.howToCook,
            model.nutrients,
            model.seasonings,
            model.imageUrl,
            category,
            model.price
        )
    }

    fun mapFromEntityList(entities: List<RecipeResponse>): List<Recipe> {
        return entities.map { mapFromEntity(it) }
    }
}
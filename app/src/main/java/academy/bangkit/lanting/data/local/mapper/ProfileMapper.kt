package academy.bangkit.lanting.data.local.mapper

import academy.bangkit.lanting.data.local.entity.ProfileEntity
import academy.bangkit.lanting.data.model.Profile
import academy.bangkit.lanting.utils.EntityMapper
import javax.inject.Inject

class ProfileMapper @Inject constructor() : EntityMapper<ProfileEntity, Profile> {
    override fun mapFromEntity(entity: ProfileEntity): Profile {
        return Profile(
            entity.id,
            entity.name,
            entity.dateOfBirth,
            entity.height,
            entity.weight,
            entity.allergy,
            entity.category,
            entity.babysBirthDate,
            entity.gestationalAge,
            entity.picture,
        )
    }

    override fun mapToEntity(model: Profile): ProfileEntity {
        return ProfileEntity(
            model.id,
            model.name,
            model.dateOfBirth,
            model.height,
            model.weight,
            model.allergy,
            model.category,
            model.babysBirthDate,
            model.gestationalAge,
            model.picture
        )
    }

    fun mapFromEntityList(entities: List<ProfileEntity>): List<Profile> {
        return entities.map { mapFromEntity(it) }
    }
}
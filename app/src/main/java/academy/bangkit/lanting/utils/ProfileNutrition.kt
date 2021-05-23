package academy.bangkit.lanting.utils

import academy.bangkit.lanting.data.model.NeededNutrition
import academy.bangkit.lanting.data.model.Profile
import academy.bangkit.lanting.data.model.ProfileCategory
import academy.bangkit.lanting.data.model.ProfileMember
import java.util.*

object ProfileNutrition {
    private val profileMember = listOf(
        ProfileMember(0, 5, ProfileCategory.BADUTA, 6, 60, 550, 9.0, 31.0, 59.0),
        ProfileMember(6, 11, ProfileCategory.BADUTA, 9, 72, 800, 15.0, 35.0, 105.0),
        ProfileMember(12, 18, ProfileCategory.BADUTA, 13, 92, 1350, 20.0, 45.0, 215.0),
        ProfileMember(10, 12, ProfileCategory.IBU, 38, 147, 1900, 55.0, 65.0, 280.0),
        ProfileMember(13, 15, ProfileCategory.IBU, 52, 156, 2050, 65.0, 70.0, 300.0),
        ProfileMember(16, 18, ProfileCategory.IBU, 55, 159, 2100, 65.0, 70.0, 300.0),
        ProfileMember(19, 29, ProfileCategory.IBU, 56, 159, 2250, 60.0, 65.0, 360.0),
        ProfileMember(30, 49, ProfileCategory.IBU, 56, 158, 2150, 60.0, 60.0, 340.0),
        ProfileMember(50, 64, ProfileCategory.IBU, 56, 158, 1800, 60.0, 50.0, 280.0),
        ProfileMember(65, 79, ProfileCategory.IBU, 53, 157, 1550, 58.0, 45.0, 230.0),
        ProfileMember(80, 80, ProfileCategory.IBU, 53, 157, 1400, 58.0, 40.0, 200.0),
        ProfileMember(1, 1, ProfileCategory.BUMIL, 0, 0, 180, 1.0, 2.3, 25.0),
        ProfileMember(2, 2, ProfileCategory.BUMIL, 0, 0, 300, 10.0, 2.3, 40.0),
        ProfileMember(3, 3, ProfileCategory.BUMIL, 0, 0, 300, 30.0, 2.3, 40.0),
        ProfileMember(1, 1, ProfileCategory.BUSUI, 0, 0, 330, 20.0, 2.2, 45.0),
        ProfileMember(2, 2, ProfileCategory.BUSUI, 0, 0, 400, 15.0, 2.2, 55.0)
    )

    private fun getNewNutrition(
        profile: Profile,
        fromMember: ProfileMember,
        correction: Double,
    ): NeededNutrition {
        val filteredProfileMember = profileMember.filter { it.category == profile.category }

        return when (profile.category) {
            ProfileCategory.BUMIL -> {
                val onTrimester = when (profile.gestationalAge) {
                    0, 1, 2, 3 -> 0
                    4, 5, 6 -> 1
                    else -> 2
                }

                val plusProfileMember = filteredProfileMember[onTrimester]
                val energy = ((fromMember.energy + plusProfileMember.energy) * correction).toInt()
                val protein = (fromMember.protein + plusProfileMember.protein) * correction
                val fat = (fromMember.fat + plusProfileMember.fat) * correction
                val carbohydrate = (fromMember.carbohydrate + plusProfileMember.carbohydrate) * correction
                NeededNutrition(energy, protein, fat, carbohydrate)
            }
            ProfileCategory.BUSUI -> {
                val babysBirthDate = profile.babysBirthDate ?: DateHelper.todayTimeStamp()
                val babysAge = DateHelper.getProfileAge(babysBirthDate, Calendar.MONTH)

                val onMonth = when {
                    babysAge <= 6 -> 0
                    else -> 1
                }

                val plusProfileMember = filteredProfileMember[onMonth]
                val energy = ((fromMember.energy + plusProfileMember.energy) * correction).toInt()
                val protein = (fromMember.protein + plusProfileMember.protein) * correction
                val fat = (fromMember.fat + plusProfileMember.fat) * correction
                val carbohydrate = (fromMember.carbohydrate + plusProfileMember.carbohydrate) * correction
                NeededNutrition(energy, protein, fat, carbohydrate)
            }
            else -> {
                val energy = (fromMember.energy * correction).toInt()
                val protein = fromMember.protein * correction
                val fat = fromMember.fat * correction
                val carbohydrate = fromMember.carbohydrate * correction
                NeededNutrition(energy, protein, fat, carbohydrate)
            }
        }
    }

    fun getNeededProfileNutrition(profile: Profile): NeededNutrition {
        val filteredProfileMember =
            if (profile.category == ProfileCategory.BADUTA) profileMember.filter { it.category == ProfileCategory.BADUTA }
            else profileMember.filter { it.category == ProfileCategory.IBU }

        val fromMember = filteredProfileMember.find {
            val selector = if (profile.category == ProfileCategory.BADUTA)
                Calendar.MONTH else Calendar.YEAR
            val age = DateHelper.getProfileAge(profile.dateOfBirth, selector)
            age >= it.minAge && age <= it.maxAge
        } ?: filteredProfileMember.last()

        val correctionFactor = profile.weight.toDouble() / fromMember.weight.toDouble()
        return getNewNutrition(profile, fromMember, correctionFactor)
    }
}

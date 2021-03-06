package academy.bangkit.lanting.data

import academy.bangkit.lanting.data.model.Profile
import android.content.Context
import java.util.*

class ProfilePreferences(context: Context) {
    companion object {
        private const val PREFS_NAME = "profile_prefs"
        private const val id = "id"
        private const val name = "name"
        private const val dateOfBirth = "date_of_birth"
        private const val height = "height"
        private const val weight = "weight"
        private const val allergy = "allergy"
        private const val category = "category"
        private const val babysBirthDate = "babys_birth_date"
        private const val gestationalAge = "gestational_age"
        private const val picture = "picture"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setProfile(profile: Profile) {
        with(preferences.edit()) {
            putInt(id, profile.id)
            putString(name, profile.name)
            putLong(dateOfBirth, profile.dateOfBirth.time)
            putInt(height, profile.height)
            putInt(weight, profile.weight)
            putString(allergy, profile.allergy)
            putString(category, profile.category.name)
            putLong(babysBirthDate, profile.babysBirthDate?.time ?: -1L)
            putInt(gestationalAge, profile.gestationalAge ?: -1)
            putString(picture, profile.picture)
            apply()
        }
    }

    val profile: Profile?
        get() {
            val name = preferences.getString(name, "")
            if (name.isNullOrEmpty()) return null

            val babysBirthDateLong = preferences.getLong(babysBirthDate, -1L)
            val gestationalAgeInt = preferences.getInt(gestationalAge, -1)

            return Profile(
                preferences.getInt(id, 0),
                name,
                Date(preferences.getLong(dateOfBirth, 0)),
                preferences.getInt(height, 0),
                preferences.getInt(weight, 0),
                preferences.getString(allergy, null),
                enumValueOf(preferences.getString(category, "") as String),
                if(babysBirthDateLong != -1L) Date(babysBirthDateLong) else null,
                if(gestationalAgeInt != -1) gestationalAgeInt else null,
                preferences.getString(picture, null)
            )
        }
}
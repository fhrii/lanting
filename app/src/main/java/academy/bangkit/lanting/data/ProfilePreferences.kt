package academy.bangkit.lanting.data

import academy.bangkit.lanting.data.model.Profile
import android.content.Context

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
        private const val picture = "picture"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setProfile(profile: Profile) {
        with(preferences.edit()) {
            putInt(id, profile.id)
            putString(name, profile.name)
            putString(dateOfBirth, profile.dateOfBirth)
            putInt(height, profile.height)
            putInt(weight, profile.weight)
            putString(allergy, profile.allergy)
            putString(category, profile.category)
            putString(picture, profile.picture)
            apply()
        }
    }

    val profile: Profile?
        get() {
            val name = preferences.getString(name, "")
            if (name.isNullOrEmpty()) return null

            return Profile(
                preferences.getInt(id, 0),
                name,
                preferences.getString(dateOfBirth, "") as String,
                preferences.getInt(height, 0),
                preferences.getInt(weight, 0),
                preferences.getString(allergy, null),
                preferences.getString(category, "") as String,
                preferences.getString(picture, null)
            )
        }
}
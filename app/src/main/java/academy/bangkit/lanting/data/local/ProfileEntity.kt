package academy.bangkit.lanting.data.local

import academy.bangkit.lanting.data.model.ProfileCategory
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LantingDatabase.TABLE_PROFILE_NAME)
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @NonNull
    val name: String,

    @NonNull
    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: String,

    @NonNull
    val height: Int,

    @NonNull
    val weight: Int,

    @Nullable
    val allergy: String?,

    @NonNull
    val category: String,

    @Nullable
    val picture: String?
)

package academy.bangkit.lanting.data.local.entity

import academy.bangkit.lanting.data.local.LantingDatabase
import academy.bangkit.lanting.data.model.ProfileCategory
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = LantingDatabase.TABLE_PROFILE_NAME)
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @NonNull
    val name: String,

    @NonNull
    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: Date,

    @NonNull
    val height: Int,

    @NonNull
    val weight: Int,

    @Nullable
    val allergy: String?,

    @NonNull
    val category: ProfileCategory,

    @Nullable
    val babysBirthDate: Date?,

    @Nullable
    val gestationalAge: Int?,

    @Nullable
    val picture: String?
)

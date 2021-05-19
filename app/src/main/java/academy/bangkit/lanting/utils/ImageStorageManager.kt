package academy.bangkit.lanting.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*

object ImageStorageManager {
    fun saveToInternalStorage(
        context: Context,
        bitmapImage: Bitmap,
    ): String {
        val fileName: String = DateHelper.generateFileName()

        context.openFileOutput(fileName, Context.MODE_PRIVATE).use { fos ->
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 90, fos)
        }

        return fileName
    }

    fun getImageFromInternalStorage(context: Context, imageFileName: String): Bitmap? {
        val directory = context.filesDir
        val file = File(directory, imageFileName)
        return BitmapFactory.decodeStream(FileInputStream(file))
    }

    fun deleteImageFromInternalStorage(context: Context, imageFileName: String): Boolean {
        val dir = context.filesDir
        val file = File(dir, imageFileName)
        return file.delete()
    }
}
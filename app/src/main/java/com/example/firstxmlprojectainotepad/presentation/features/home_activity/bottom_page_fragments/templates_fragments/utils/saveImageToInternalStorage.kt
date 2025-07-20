package com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.templates_fragments.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


fun saveImageToInternalStorage(context: Context, uri: Uri): String {
    val inputStream = context.contentResolver.openInputStream(uri)
    val fileName = "image_${System.currentTimeMillis()}.jpg"
    val file = File(context.filesDir, fileName)
    val outputStream = FileOutputStream(file)

    inputStream?.copyTo(outputStream)
    inputStream?.close()
    outputStream.close()

    return file.absolutePath
}
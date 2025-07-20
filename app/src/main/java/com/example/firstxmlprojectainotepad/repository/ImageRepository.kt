package com.example.firstxmlprojectainotepad.repository

import androidx.lifecycle.LiveData
import com.example.firstxmlprojectainotepad.data.ImageDao
import com.example.firstxmlprojectainotepad.data.ImageEntity
import kotlinx.coroutines.flow.Flow

class ImageRepository(private val imageDao: ImageDao) {
    val allImages: Flow<List<ImageEntity>> = imageDao.getAllImages()

    suspend fun insert(image: ImageEntity) {
        imageDao.insertImage(image)
    }

    suspend fun delete(image: ImageEntity) {
        imageDao.deleteImage(image)
    }
}

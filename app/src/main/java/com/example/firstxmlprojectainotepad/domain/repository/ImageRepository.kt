package com.example.firstxmlprojectainotepad.domain.repository

import com.example.firstxmlprojectainotepad.data.DAO.ImageDao
import com.example.firstxmlprojectainotepad.data.entities.ImageEntity
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

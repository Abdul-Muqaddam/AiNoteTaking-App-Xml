package com.example.firstxmlprojectainotepad.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "image_path") val imagePath: String,
    @ColumnInfo(name = "title") val title: String? = null
)

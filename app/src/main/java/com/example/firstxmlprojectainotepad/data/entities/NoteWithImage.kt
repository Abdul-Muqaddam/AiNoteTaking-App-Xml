package com.example.firstxmlprojectainotepad.data.entities


import androidx.room.Embedded
import androidx.room.Relation

data class NoteWithImages(
    @Embedded val note: NoteEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "noteOwnerId"
    )
    val images: List<ImageEntity>
)

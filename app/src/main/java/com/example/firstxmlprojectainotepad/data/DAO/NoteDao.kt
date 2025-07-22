package com.example.firstxmlprojectainotepad.data.DAO

import androidx.room.*
import com.example.firstxmlprojectainotepad.data.entities.ImageEntity
import com.example.firstxmlprojectainotepad.data.entities.NoteEntity
import com.example.firstxmlprojectainotepad.data.entities.NoteWithImages
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    // Insert a note and return the generated note ID (used to link images)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    // Insert multiple images (use the noteId returned above to set noteOwnerId)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<ImageEntity>)

    // Get all notes with their related images
    @Transaction
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotesWithImages(): Flow<List<NoteWithImages>>

    // Delete a note by entity
    @Delete
    suspend fun deleteNote(note: NoteEntity)

    // Delete all images related to a note by noteId
    @Query("DELETE FROM images WHERE noteOwnerId = :noteId")
    suspend fun deleteImagesByNoteId(noteId: Int)

    // Get a specific note with its images by noteId
    @Transaction
    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Long): NoteWithImages?
}

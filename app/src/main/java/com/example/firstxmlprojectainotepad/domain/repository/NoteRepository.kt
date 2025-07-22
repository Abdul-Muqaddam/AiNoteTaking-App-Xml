package com.example.firstxmlprojectainotepad.domain.repository


import com.example.firstxmlprojectainotepad.data.DAO.NoteDao
import com.example.firstxmlprojectainotepad.data.entities.NoteEntity
import com.example.firstxmlprojectainotepad.data.entities.ImageEntity
import com.example.firstxmlprojectainotepad.data.entities.NoteWithImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun insertNote(note: NoteEntity): Long {
        return withContext(Dispatchers.IO) {
            noteDao.insertNote(note)
        }
    }

//    suspend fun insertImages(images: List<ImageEntity>) {
//        return withContext(Dispatchers.IO) {
//            noteDao.insertImage(images)
//        }
//    }

    suspend fun getAllNotesWithImages(): Flow<List<NoteWithImages>> {
        return withContext(Dispatchers.IO) {
            noteDao.getAllNotesWithImages()
        }
    }

    suspend fun getNoteById(id: Long): NoteWithImages? {
        return withContext(Dispatchers.IO) {
            noteDao.getNoteById(id)
        }
    }

}

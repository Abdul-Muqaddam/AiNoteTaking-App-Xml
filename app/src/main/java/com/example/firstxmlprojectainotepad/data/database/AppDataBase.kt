package com.example.firstxmlprojectainotepad.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firstxmlprojectainotepad.data.DAO.ImageDao
import com.example.firstxmlprojectainotepad.data.DAO.NoteDao
import com.example.firstxmlprojectainotepad.data.entities.ImageEntity
import com.example.firstxmlprojectainotepad.data.entities.NoteEntity

@Database(
    entities = [NoteEntity::class, ImageEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun imageDao(): ImageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

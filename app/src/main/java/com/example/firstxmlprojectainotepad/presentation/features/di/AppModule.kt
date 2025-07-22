package com.example.firstxmlprojectainotepad.presentation.features.di

import androidx.room.Room
import com.example.firstxmlprojectainotepad.data.database.AppDatabase
import com.example.firstxmlprojectainotepad.domain.repository.ImageRepository
import com.example.firstxmlprojectainotepad.domain.repository.NoteRepository
import com.example.firstxmlprojectainotepad.presentation.features.Notes_Screen.NoteViewModel
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.templates_fragments.TemplateViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    // Provide Database
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "image_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    // Provide DAOs
    single { get<AppDatabase>().imageDao() }
    single { get<AppDatabase>().noteDao() }

    // Provide Repositories
    single { ImageRepository(get()) }
    single { NoteRepository(get()) }

    // Provide ViewModels
    viewModelOf(::TemplateViewModel)
    viewModelOf(::NoteViewModel)


}

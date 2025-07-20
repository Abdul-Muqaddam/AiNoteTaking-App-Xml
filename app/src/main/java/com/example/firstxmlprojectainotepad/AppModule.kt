package com.example.firstxmlprojectainotepad

import androidx.room.Room
import com.example.firstxmlprojectainotepad.data.AppDatabase
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.templates_fragments.TemplateViewModel
import com.example.firstxmlprojectainotepad.repository.ImageRepository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Provide Database
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "image_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    // Provide DAO
    single { get<AppDatabase>().imageDao() }

    // Provide Repository
    single { ImageRepository(get()) }

    // Provide ViewModel
    viewModel { TemplateViewModel(get()) }
}

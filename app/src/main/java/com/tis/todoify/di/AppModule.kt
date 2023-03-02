package com.tis.todoify.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tis.todoify.data.local.NoteDatabase
import com.tis.todoify.data.local.repository.NoteRepository
import com.tis.todoify.domain.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application) : NoteDatabase{
        return Room.databaseBuilder(
            context = app,
            klass = NoteDatabase::class.java,
            name = "note_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }
}
package com.tis.todoify.data.local

import androidx.room.*
import com.tis.todoify.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAllNote(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE title LIKE :title")
    fun findByTitle(title: String): Flow<Note?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)
}
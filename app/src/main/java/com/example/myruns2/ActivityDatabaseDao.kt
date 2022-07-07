package com.example.myruns2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ActivityDatabaseDao {
    @Insert
     fun insertActivity(table: Table)

    @Query("SELECT * FROM ExerciseEntry")
    fun getAllData(): Flow<List<Table>>

    @Query("DELETE FROM ExerciseEntry")
     fun deleteAll()

    @Query("DELETE FROM ExerciseEntry WHERE id = :key")
     fun deleteEntry(key:Long)


}
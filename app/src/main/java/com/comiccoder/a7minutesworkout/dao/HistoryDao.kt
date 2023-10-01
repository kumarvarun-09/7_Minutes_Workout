package com.comiccoder.a7minutesworkout.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.comiccoder.a7minutesworkout.models.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    fun insert(history: HistoryEntity)

    @Query("SELECT * FROM `history-table`")
    fun fetchAllDates(): Flow<List<HistoryEntity>>
}
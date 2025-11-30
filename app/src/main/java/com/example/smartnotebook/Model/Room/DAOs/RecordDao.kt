package com.example.smartnotebook.Model.Room.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartnotebook.Model.Room.Entities.RecordsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE) // При конфликте - заменить
    suspend fun insertRecord(record_data: RecordsEntity) : Long

    // Получение данных о пользователе по ID
    @Query("Select * from records_table where record_id = :id")
    fun getCurrentRecordsData(id: Long) : Flow<RecordsEntity?>

    @Query("SELECT * FROM records_table")
    fun getAllRecordsData() : Flow<List<RecordsEntity>>


    @Query("SELECT record_id FROM records_table ORDER BY record_id DESC LIMIT 1")
    suspend fun getLastRecordId() : Long?

    @Query("DELETE FROM records_table")
    suspend fun clearRecordsTable()
}
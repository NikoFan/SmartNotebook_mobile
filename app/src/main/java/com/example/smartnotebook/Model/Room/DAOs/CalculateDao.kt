package com.example.smartnotebook.Model.Room.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartnotebook.Model.Room.Entities.CalculateEntity

@Dao
interface CalculateDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE) // При конфликте - заменить
    suspend fun insertCalcData(calculate_data: CalculateEntity)

    // Получение данных о пользователе по ID
    @Query("Select * from calculate_table where calculate_id = :id")
    fun getAllCalculateData(id: Long) : CalculateEntity?
}
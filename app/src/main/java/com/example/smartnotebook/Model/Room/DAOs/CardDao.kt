package com.example.smartnotebook.Model.Room.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartnotebook.Model.Room.Entities.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE) // При конфликте - заменить
    suspend fun insertCard(card_data: CardEntity)

    // Получение данных о пользователе по ID
    @Query("Select * from card_table")
    fun getAllCardsData() : Flow<List<CardEntity>>


    @Query("DELETE FROM card_table")
    suspend fun deleteCardsData()
}
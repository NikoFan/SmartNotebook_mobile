package com.example.smartnotebook.Model.Room.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartnotebook.Model.Room.Entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE) // При конфликте - заменить
    suspend fun insertUser(user: UserEntity)

    // Получение данных о пользователе по ID
    @Query("Select * from users_table where user_id = :id")
    fun getUserData(id: Long) : UserEntity?
}
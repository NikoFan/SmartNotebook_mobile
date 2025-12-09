package com.example.smartnotebook.Model.Room.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartnotebook.Model.Room.Entities.RecordsEntity
import com.example.smartnotebook.Model.Room.Entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE) // При конфликте - заменить
    suspend fun insertUser(user: UserEntity) : Long

    // Получение данных о пользователе по ID
    @Query("Select * from users_table where user_id = :id")
    fun getUserData(id: Long) : UserEntity?

//    @Query("Select * from users_table")
//    fun getUsersData() : Flow<List<UserEntity>>

    @Query("SELECT user_id FROM users_table WHERE " +
            "login = :inputLoginData AND password = :inputPasswordData")
    fun getUserId(
        inputLoginData: String,
        inputPasswordData: String
    ) : Long?
}
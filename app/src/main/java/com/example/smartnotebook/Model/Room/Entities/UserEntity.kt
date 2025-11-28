package com.example.smartnotebook.Model.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserEntity (
    @PrimaryKey(autoGenerate = true) // Автосоздание ключей
    val user_id: Long = 0,

    val login: String
)
package com.example.smartnotebook.Model.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records_table")
data class RecordsEntity(
    @PrimaryKey(autoGenerate = true)
    val record_id: Long = 0,

    val name: String,
    val category: String,
    val description: String,
    val goal_date: Long,
    val create_date: Long // Используется в качестве отсчета в мл.с.
)
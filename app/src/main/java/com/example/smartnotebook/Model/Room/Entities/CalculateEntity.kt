package com.example.smartnotebook.Model.Room.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "calculate_table",
    foreignKeys = [
        ForeignKey(
            entity = CardEntity::class,
            parentColumns = ["card_id"],
            childColumns = ["card_id_fk"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class CalculateEntity(
    @PrimaryKey(autoGenerate = true)
    val calculate_id: Long = 0,
    val date: Long, // Дата использования
    val card_id_fk: Long

)

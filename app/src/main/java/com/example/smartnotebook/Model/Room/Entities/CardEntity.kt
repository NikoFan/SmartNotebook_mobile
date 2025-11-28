package com.example.smartnotebook.Model.Room.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "card_table",
    foreignKeys = [
        ForeignKey(
            entity = RecordsEntity::class,
            parentColumns = ["record_id"],
            childColumns = ["record_id_fk"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CardEntity (
    @PrimaryKey(autoGenerate = true)
    val card_id: Long = 0,

    val streak_count: Long, // Использовано подряд
    val last_usage: Long, // Дата последнего использования
    val all_usage_count: Long, // Использований с даты создания
    val record_id_fk: Long // FK на RecordsEntity
)
package com.example.smartnotebook.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnotebook.Model.Room.DAOs.CardDao
import com.example.smartnotebook.Model.Room.DAOs.RecordDao
import com.example.smartnotebook.Model.Room.Entities.CardEntity
import com.example.smartnotebook.Model.Room.Entities.RecordsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivityVM(private val cardsDao: CardDao, private val recordDao: RecordDao) : ViewModel() {

    val allCards = cardsDao.getAllCardsData()

    val allRecords = recordDao.getAllRecordsData()

    fun getCurrentRec(id: Long) : Flow<RecordsEntity?>{
        return recordDao.getCurrentRecordsData(id=id)
    }

    fun AddNewCard(){
        viewModelScope.launch {
            try {
                // 1. Вставляем запись и получаем её ID напрямую
                val recordId = recordDao.insertRecord(
                    RecordsEntity(
                        name = "Прогулка",
                        category = "Активность ${System.currentTimeMillis()}",
                        create_date = System.currentTimeMillis()
                    )
                )

                // 2. Используем полученный ID для карточки
                cardsDao.insertCard(
                    CardEntity(
                        streak_count = 0,
                        last_usage = System.currentTimeMillis(),
                        all_usage_count = 1,
                        record_id_fk = recordId // ← сразу используем ID
                    )
                )

                println("Создана запись с ID: $recordId")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun clearAllData() {
        viewModelScope.launch {
            try {
                cardsDao.deleteCardsData() // если есть
                recordDao.clearRecordsTable()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addCard() {
        println("delete old")
//        viewModelScope.launch {
//            cardsDao.deleteCardsData()
//        }
//
//        viewModelScope.launch {
//            recordDao.clearRecordsTable()
//        }
        println("add new")
        // Добавление нового хобби
        viewModelScope.launch {
            recordDao.insertRecord(
                RecordsEntity(
                    name="Прогулка",
                    category = "Активность22",
                    create_date = System.currentTimeMillis()
                )
            )

        }

        viewModelScope.launch {
            cardsDao.insertCard(
                CardEntity(
                    streak_count = 0,
                    last_usage = System.currentTimeMillis(),
                    all_usage_count = 1,
                    record_id_fk = 1
                )
            )
        }


    }


}
package com.example.smartnotebook.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnotebook.Model.Room.DAOs.RecordDao
import com.example.smartnotebook.Model.Room.Entities.RecordsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivityVM(private val record_dao: RecordDao) : ViewModel() {


    val allRecords = record_dao.getAllRecordsData()

    fun getCurrentRec(id: Long) : Flow<RecordsEntity?>{
        return record_dao.getCurrentRecordsData(id=id)
    }

    fun AddNewCard(){
        println("Пока не работает")
//        viewModelScope.launch {
//            try {
//                // 1. Вставляем запись и получаем её ID напрямую
//                val recordId = recordDao.insertRecord(
//                    RecordsEntity(
//                        name = "Прогулка",
//                        category = "Активность ${System.currentTimeMillis()}",
//                        create_date = System.currentTimeMillis()
//                    )
//                )
//
//                println("Создана запись с ID: $recordId")
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
    }

    fun clearAllData() {
        viewModelScope.launch {
            try {
                record_dao.clearRecordsTable()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addCard() {
        println("Пока не функционирует")
//        viewModelScope.launch {
//            cardsDao.deleteCardsData()
//        }
//
//        viewModelScope.launch {
//            recordDao.clearRecordsTable()
//        }
//        println("add new")
//        // Добавление нового хобби
//        viewModelScope.launch {
//            recordDao.insertRecord(
//                RecordsEntity(
//                    name="Прогулка",
//                    category = "Активность22",
//                    create_date = System.currentTimeMillis()
//                )
//            )
//
//        }
//
//        viewModelScope.launch {
//            cardsDao.insertCard(
//                CardEntity(
//                    streak_count = 0,
//                    last_usage = System.currentTimeMillis(),
//                    all_usage_count = 1,
//                    record_id_fk = 1
//                )
//            )
//        }
//    }
    }



}
package com.example.smartnotebook.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnotebook.Model.Room.DAOs.RecordDao
import com.example.smartnotebook.Model.Room.Entities.RecordsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivityVM(private val record_dao: RecordDao) : ViewModel() {


    val allRecords = record_dao.getAllRecordsData()

    fun GetCurrentRec(id: Long) : Flow<RecordsEntity?>{
        return record_dao.getCurrentRecordsData(id=id)
    }

    fun AddNewCard(){
        println("Пока не работает")

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

    }



}
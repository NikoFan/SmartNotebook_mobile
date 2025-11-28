package com.example.smartnotebook.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartnotebook.Model.Room.AppDatabase
import com.example.smartnotebook.Model.Room.Entities.CardEntity
import com.example.smartnotebook.ViewModel.MainActivityVM
import com.example.smartnotebook.ui.theme.SmartNotebookTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            SmartNotebookTheme {
                UiConstructor()
            }
        }
    }

    @Composable
    private fun UiConstructor(){ // Конструктор интерфейса
        val database = AppDatabase.getInstance(applicationContext)
        val cardsDao = database.cardDao()
        val recordsDao = database.recordDao()
        val vm_instance = MainActivityVM(
            cardsDao = cardsDao,
            recordDao = recordsDao)

        // Добавление данных
        vm_instance.addCard()
        Column(
            verticalArrangement = Arrangement.Center, // Все объекты по центру (Вертикально)
            horizontalAlignment = Alignment.CenterHorizontally, // Все объекты по середине (Горизонтально)
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            Text(text = "text message")
            CreateOldRecordsViewDisplay(
                viewModelInstance = vm_instance
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        SmartNotebookTheme {
            UiConstructor()
        }
    }
}

@Composable
fun CreateOldRecordsViewDisplay(
    viewModelInstance: MainActivityVM
){
    val records_data by viewModelInstance.allCards.collectAsStateWithLifecycle(initialValue = emptyList())
    Text(
        text = "Message"
    )
    println("cards: $records_data")
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(records_data) {
            card ->
            Text(text = "Карточка ${card.streak_count}")
            Text(text = "FK: ${card.record_id_fk}")
            Text(text = "Категория ${
                viewModelInstance.getCurrentRec(
                    id = card.record_id_fk
                ).collectAsStateWithLifecycle(null).value?.category
            }")
            println(viewModelInstance.getCurrentRec(
                id = card.record_id_fk
            ))
        }
    }

}




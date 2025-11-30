package com.example.smartnotebook.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartnotebook.Model.Room.AppDatabase
import com.example.smartnotebook.ViewModel.MainActivityVM
import com.example.smartnotebook.ui.theme.SmartNotebookTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.smartnotebook.R
import com.example.smartnotebook.View.WidgtesCreateSupport.ButtonWidgets
import com.example.smartnotebook.View.WidgtesCreateSupport.TextWidgets


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
        // vm_instance.addCard()
        Column(
            verticalArrangement = Arrangement.Center, // Все объекты по центру (Вертикально)
            horizontalAlignment = Alignment.CenterHorizontally, // Все объекты по середине (Горизонтально)
            modifier = Modifier
                .background(color = colorResource(R.color.main_background_color))
                .fillMaxSize()
                .systemBarsPadding()
        ) {

            CreateOldRecordsViewDisplay(
                viewModelInstance = vm_instance
            )

            ButtonWidgets.CreateAcceptButton(
                text = "Выполнил",
                onClick = { vm_instance.AddNewCard() }
            )

            // Кнопка: очистить всё
            Button(
                onClick = { vm_instance.clearAllData() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Очистить всё")
            }
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
    // Карточки из Room
    val cards_data by viewModelInstance.allCards.collectAsStateWithLifecycle(
        initialValue = emptyList()
    )
    TextWidgets.CreateWindowTitleWidget(
        "Записи"
    )
    println("cards: ${cards_data.size}")


    // Область вертикальной прокрутки
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 18.dp),
        modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth()
    ) {
        items(cards_data) {
            card ->

            // Карточки
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .border(
                            width = 0.2f.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                    ) {
                    TextWidgets.CreateWindowTitleWidget(
                        text_message = viewModelInstance.getCurrentRec(
                            id = card.record_id_fk
                        ).collectAsStateWithLifecycle(null).value?.name
                    )
                    Text(text = "ИМЯ ${
                        viewModelInstance.getCurrentRec(
                            id = card.record_id_fk
                        ).collectAsStateWithLifecycle(null).value?.name
                    }")
                    Text(text = "Карточка ${card.streak_count}")
                    Text(text = "FK: ${card.record_id_fk}")
                    Text(text = "Категория ${
                        viewModelInstance.getCurrentRec(
                            id = card.record_id_fk
                        ).collectAsStateWithLifecycle(null).value?.category
                    }")

                    ButtonWidgets.CreateAcceptButton(
                        text = "Выполнил",
                        onClick = { viewModelInstance.AddNewCard() }
                    )
                }
            }
        }
    }

}




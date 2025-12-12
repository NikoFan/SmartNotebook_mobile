package com.example.smartnotebook.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartnotebook.ViewModel.MainActivityVM
import com.example.smartnotebook.ui.theme.SmartNotebookTheme
import com.example.smartnotebook.MyApplication
import com.example.smartnotebook.R
import com.example.smartnotebook.View.WidgtesCreateSupport.TextWidgets
import com.example.smartnotebook.ViewModel.StaticClass


class MainActivity : ComponentActivity() {

    private val app: MyApplication // Room
        get() = applicationContext as MyApplication


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
        val vm_instance = MainActivityVM(
            record_dao = app.database.recordDao())

        println(StaticClass.activeAccountId)

        val listOfText = listOf<String>("Запись", "12345678901011121314151617181920212223242526272829303132", "Игра")

        Column(
            verticalArrangement = Arrangement.Center, // Все объекты по центру (Вертикально)
            horizontalAlignment = Alignment.CenterHorizontally, // Все объекты по середине (Горизонтально)
            modifier = Modifier
                .background(color = colorResource(R.color.main_background_color))
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            TextWidgets.Create_WindowTitleTextWidget(
                contentTextData = "Дневник"
            )


            // Отображение записей
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.6f)
                    .padding(10.dp)
            ) {
                items(listOfText){ recordExample ->
                    TextWidgets.Create_RecordDisplayText(
                        content = recordExample
                    ) {
                        println("Открытие записи $recordExample")
                    }

                }
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


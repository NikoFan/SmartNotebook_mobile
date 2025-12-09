package com.example.smartnotebook.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartnotebook.Model.Room.AppDatabase
import com.example.smartnotebook.MyApplication
import com.example.smartnotebook.R
import com.example.smartnotebook.View.WidgtesCreateSupport.ButtonWidgets
import com.example.smartnotebook.View.WidgtesCreateSupport.TextFieldWidget
import com.example.smartnotebook.View.WidgtesCreateSupport.TextWidgets
import com.example.smartnotebook.View.ui.theme.SmartNotebookTheme
import com.example.smartnotebook.ViewModel.LoginActivityVM
import com.example.smartnotebook.ViewModel.MainActivityVM

class LoginActivity : ComponentActivity() {
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
        val vm_instance = LoginActivityVM(
            user_dao = app.database.userDao())
        Column(
            verticalArrangement = Arrangement.Center, // Все объекты по центру (Вертикально)
            horizontalAlignment = Alignment.CenterHorizontally, // Все объекты по середине (Горизонтально)
            modifier = Modifier
                .background(color = colorResource(R.color.main_background_color))
                .fillMaxSize()
                .systemBarsPadding()
        ) {

            // Заголовок
            TextWidgets.Create_WindowTitleTextWidget(
                widget_text = "Авторизация"
            )
            // Инпут логина
            var login by remember { mutableStateOf("") }
            TextFieldWidget.Create_UserMainInputField(
                hint_param = "Логин",
                value_param = login,
                label_param = "Логин от аккаунта",
                placeholder_param = "Введите...",
                onValueChange_param = {new ->
                    login = new
                }
            )

            // Инпут пароля
            var password by remember { mutableStateOf("") }
            TextFieldWidget.Create_UserMainInputField(
                hint_param = "Пароль",
                value_param = password,
                label_param = "Пароль от аккаунта",
                placeholder_param = "Введите...",
                onValueChange_param = {new ->
                    password = new
                }
            )




            ButtonWidgets.Create_CreateNewRecord_Button {
                println(
                    "${login}\t${password}"
                )
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

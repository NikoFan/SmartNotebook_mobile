package com.example.smartnotebook.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartnotebook.MyApplication
import com.example.smartnotebook.R
import com.example.smartnotebook.View.WidgtesCreateSupport.ButtonWidgets
import com.example.smartnotebook.View.WidgtesCreateSupport.TextFieldWidget
import com.example.smartnotebook.View.WidgtesCreateSupport.TextWidgets
import com.example.smartnotebook.View.ui.theme.SmartNotebookTheme
import com.example.smartnotebook.ViewModel.LoginActivityVM

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
        val viewModelInstance = remember {
            LoginActivityVM(
                userDaoInstance = app.database.userDao())
        }


        // Ожидание изменений в viewModelInstance.activeUserIdNumber
        LaunchedEffect(viewModelInstance.activeUserIdNumber) {
            if (viewModelInstance.activeUserIdNumber != null) {
                // Пользователь найден
                println("Correct with ID: ${viewModelInstance.activeUserIdNumber}")

            } else {
                println("no result: ${viewModelInstance.activeUserIdNumber}")
            }
        }
        LazyColumn (
            verticalArrangement = Arrangement.Center, // Все объекты по центру (Вертикально)
            horizontalAlignment = Alignment.CenterHorizontally, // Все объекты по середине (Горизонтально)
            modifier = Modifier
                .background(color = colorResource(R.color.main_background_color))
                .fillMaxSize()
                .systemBarsPadding()
        ) {

            item {
                // Заголовок
                TextWidgets.Create_WindowTitleTextWidget(
                    contentTextData = "Авторизация"
                )
            }

            item {
                // Инпут логина
                var loginInput by remember { mutableStateOf("") }
                TextFieldWidget.Create_UserMainInputField(
                    hintParam = "Логин",
                    valueParam = loginInput,
                    labelParam = "Логин от аккаунта",
                    placeholderParam = "Введите...",
                    onValueChangeParam = { new ->
                        loginInput = new
                    }
                )

                // Инпут пароля
                var passwordInput by remember { mutableStateOf("") }
                TextFieldWidget.Create_UserMainInputField(
                    hintParam = "Пароль",
                    valueParam = passwordInput,
                    labelParam = "Пароль от аккаунта",
                    placeholderParam = "Введите...",
                    onValueChangeParam = { new ->
                        passwordInput = new
                    }
                )

                ButtonWidgets.Create_MainAccept_Button(
                    contentData = "Войти"
                ){
                    viewModelInstance.SearchUserByInputData(
                        login = loginInput,
                        pass = passwordInput
                    )
                }
                ButtonWidgets.Create_SecondAction_Button(
                    contentData = "Создать аккаунт"
                ){
                    println(
                        "Создать аккаунт"
                    )
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

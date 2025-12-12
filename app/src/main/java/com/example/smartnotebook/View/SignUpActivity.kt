package com.example.smartnotebook.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartnotebook.MyApplication
import com.example.smartnotebook.R
import com.example.smartnotebook.View.WidgtesCreateSupport.ButtonWidgets
import com.example.smartnotebook.View.WidgtesCreateSupport.ErrorsShowsWidgets
import com.example.smartnotebook.View.WidgtesCreateSupport.TextFieldWidget
import com.example.smartnotebook.View.WidgtesCreateSupport.TextWidgets
import com.example.smartnotebook.View.ui.theme.SmartNotebookTheme
import com.example.smartnotebook.ViewModel.LoginActivityVM
import com.example.smartnotebook.ViewModel.SignUpActivityVM

class SignUpActivity : ComponentActivity() {
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

        val context: Context = LocalContext.current

        // Объект view model
        val viewModelInstance = remember {
            SignUpActivityVM(
                userDaoInstance = app.database.userDao())
        }

        // Атрибуты ошибки
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope() // Корутина


        // Ожидание изменений в viewModelInstance.activeUserIdNumber
        LaunchedEffect(viewModelInstance.isLoginAndPasswordExist) {
            if (!viewModelInstance.isLoginAndPasswordExist) {
                // Пользователь найден
                println("Аккаунта с такими данными не существует: ${viewModelInstance.isLoginAndPasswordExist}")
                startActivity(
                    Intent(
                        context,
                        LoginActivity::class.java
                    )
                )

            } else {
                println("аккаунт уже занят: ${viewModelInstance.isLoginAndPasswordExist}")
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
                    contentTextData = "Регистрация"
                )
            }

            item {
                // Инпут логина
                var loginInput by remember { mutableStateOf("") }
                TextFieldWidget.Create_UserMainInputField(
                    hintParam = "Логин",
                    valueParam = loginInput,
                    labelParam = "Придумайте логин",
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
                    labelParam = "Придумайте пароль",
                    placeholderParam = "Введите...",
                    onValueChangeParam = { new ->
                        passwordInput = new
                    }
                )

                ButtonWidgets.Create_MainAccept_Button(
                    contentData = "Создать аккаунт"
                ){
                    viewModelInstance.SearchUserByInputDataAndAddUser(
                        login = loginInput,
                        pass = passwordInput
                    )

                    if (viewModelInstance.isLoginAndPasswordExist == true){
                        // Вызов всплывающего сообщения
                        ErrorsShowsWidgets.CallSnackBar(
                            snackbarHostState = snackbarHostState,
                            scope = scope
                        )
                    }
                }
                ButtonWidgets.Create_SecondAction_Button(
                    contentData = "войти в существующий!"
                ){
                    startActivity(
                        Intent(
                            context,
                            LoginActivity::class.java
                        )
                    )
                }
            }
            item {
                // Показ ошибки
                if (viewModelInstance.signInError != null) {
                    ErrorsShowsWidgets.ErrorSnackBar(
                        message = viewModelInstance.signInError.toString(),
                        snackbarHostState = snackbarHostState
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
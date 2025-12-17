package com.example.smartnotebook.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnotebook.Model.LoginRequest
import com.example.smartnotebook.RetrofitActions.ApiFactory
import kotlinx.coroutines.launch
import org.json.JSONObject

class SignUpActivityVM : ViewModel() {

    // Существует ли дубликат нового аккаунта для данных
    private var _isLoginAndPasswordExist by mutableStateOf<Boolean>(true)
    val isLoginAndPasswordExist: Boolean get() = _isLoginAndPasswordExist

    private var _newUserAccountId by mutableStateOf<Long?>(null)
    val newUserAccountId: Long? get() = _newUserAccountId

    // Статус ошибки
    private var _statusSignInError by mutableStateOf<String?>(null)
    val statusSignInError: String? get() = _statusSignInError

    // Проверка соответствия почты
    fun isValidCustomEmail(email: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        return regex.matches(email)
    }

    fun registerUserFromDatabase(
        login: String,
        password: String,
        mail: String
    ){
        _statusSignInError = null
        if (login.isEmpty() or password.isEmpty()){
            _statusSignInError = "Заполните пустые поля"
            return
        }
        if (!isValidCustomEmail(email = mail)){
            _statusSignInError = "Почта не корректна"
            return
        }
//        viewModelScope.launch {
//            try {
//                val request = LoginRequest(
//                    user_login = login,
//                    user_password = password)
//                val response = ApiFactory.apiService.login(
//                    request = request)
//
//                if (response.isSuccessful) {
//                    val user = response.body()
//                    // Успешная регистрация — перейти на главный экран
//                    if (user != null){
//                        println(user)
//                        _newUserAccountId = user.user_id.toLong()
//                    } else {
//                        _statusSignInError = "Ошибка на сервере"
//                    }
//
//                } else {
//                    val errorBody = response.errorBody()?.string()
//                    val errorMessage = try {
//                        // Парсим JSON вручную или через Gson
//                        val errorJson = JSONObject(errorBody)
//                        errorJson.getString("detail") // ← "Invalid password"
//                    } catch (e: Exception) {
//                        "Проверьте данные"
//                    }
//                    _statusSignInError = errorMessage
//                }
//            } catch (e: Exception) {
//                // Сетевая ошибка, таймаут и т.д.
//                _statusSignInError = "Ошибка отправки данных"
//            }
//        }
    }
}
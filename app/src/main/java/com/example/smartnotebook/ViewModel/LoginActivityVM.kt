package com.example.smartnotebook.ViewModel


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnotebook.Model.LoginRequest
import com.example.smartnotebook.RetrofitActions.ApiFactory


import kotlinx.coroutines.launch
import org.json.JSONObject

class LoginActivityVM : ViewModel() {

    private var _activeUserIdNumber by mutableStateOf<Long?>(null)
    val activeUserIdNumber: Long? get() = _activeUserIdNumber

    // Статус ошибки
    private var _statusSignInError by mutableStateOf<String?>(null)
    val statusSignInError: String? get() = _statusSignInError

    fun logInClientByInputData(
        login: String,
        password: String
    ){
        _statusSignInError = null
        if (login.isEmpty() or password.isEmpty()){
            _statusSignInError = "Заполните пустые поля"
            return
        }
        viewModelScope.launch {
            try {
                val request = LoginRequest(
                    user_login = login,
                    user_password = password)
                val response = ApiFactory.apiService.login(
                    request = request)

                if (response.isSuccessful) {
                    val user = response.body()
                    // Успешная регистрация — перейти на главный экран
                    if (user != null){
                        println(user)
                        _activeUserIdNumber = user.user_id.toLong()
                    } else {
                        _statusSignInError = "Ошибка на сервере"
                    }

                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = try {
                        // Парсим JSON вручную или через Gson
                        val errorJson = JSONObject(errorBody)
                        errorJson.getString("detail") // ← "Invalid password"
                    } catch (e: Exception) {
                        "Проверьте данные"
                    }
                    _statusSignInError = errorMessage
                }
            } catch (e: Exception) {
                // Сетевая ошибка, таймаут и т.д.
                _statusSignInError = "Ошибка отправки данных"
            }
        }
    }

}
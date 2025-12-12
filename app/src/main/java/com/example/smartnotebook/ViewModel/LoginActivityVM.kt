package com.example.smartnotebook.ViewModel


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnotebook.Model.Room.DAOs.UserDao
import com.example.smartnotebook.Model.Room.Entities.UserEntity
import kotlinx.coroutines.launch

class LoginActivityVM(private val userDaoInstance: UserDao) : ViewModel() {

    private var _activeUserIdNumber by mutableStateOf<Long?>(null)
    val activeUserIdNumber: Long? get() = _activeUserIdNumber

    // Статус ошибки
    private var _signInError by mutableStateOf<String?>(null)
    val signInError: String? get() = _signInError
    fun SearchUserByInputData(login: String, pass: String) {

        if (login.isNotEmpty() && pass.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val userId = userDaoInstance.getUserId(
                        inputLoginData = login, // neoleg
                        inputPasswordData = pass
                    ) // 123
                    // Проверка ответа Room
                    if (userId != null) {
                        _activeUserIdNumber = userId
                        _signInError = null
                    } else {
                        _signInError = "Неверный логин или пароль"
                    }

                } catch (e: Exception) {
                    _signInError = "Ошибка на сервере"
                    e.printStackTrace()
                }
            }
            return
        }
        _signInError = "Заполните все поля"
    }
}
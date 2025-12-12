package com.example.smartnotebook.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnotebook.Model.Room.DAOs.UserDao
import com.example.smartnotebook.Model.Room.Entities.UserEntity
import kotlinx.coroutines.launch

class SignUpActivityVM(private val userDaoInstance: UserDao) : ViewModel() {

    // Прошел ли проверку
    private var _isLoginAndPasswordExist by mutableStateOf<Boolean>(true)
    val isLoginAndPasswordExist: Boolean get() = _isLoginAndPasswordExist

    private var _newUserAccountId by mutableStateOf<Long?>(null)
    val newUserAccountId: Long? get() = _newUserAccountId

    // Статус ошибки
    private var _signInError by mutableStateOf<String?>(null)
    val signInError: String? get() = _signInError


    fun InsertNewUserToRoom(
        userEntityExample: UserEntity
    ) {
        viewModelScope.launch {
            try {
                _newUserAccountId = userDaoInstance.insertUser(
                    user = userEntityExample
                )

            } catch (e: Exception) {
                _signInError = "Ошибка добавления пользователя"
                e.printStackTrace()
            }
        }
    }

    fun SearchExistUserAccountByInputData(login: String, pass: String) {

        if (login.isNotEmpty() && pass.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val userId = userDaoInstance.getUserId(
                        inputLoginData = login, // neoleg
                        inputPasswordData = pass
                    ) // 123
                    // Проверка ответа Room
                    if (userId != null) {
                        _signInError = "Введеные данные недоступны"
                    } else {
                        _isLoginAndPasswordExist = false
                        _signInError = null
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
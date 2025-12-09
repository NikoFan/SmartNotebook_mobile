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


    fun SearchUserByInputData(login: String, pass: String) {
        if (login.isNotEmpty() && pass.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val userId = userDaoInstance.getUserId(
                        inputLoginData = login, // neoleg
                        inputPasswordData = pass) // 123
                    println("taken result: $userId")
                    _activeUserIdNumber = userId
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
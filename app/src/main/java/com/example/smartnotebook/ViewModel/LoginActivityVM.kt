package com.example.smartnotebook.ViewModel


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnotebook.Model.Room.DAOs.UserDao
import kotlinx.coroutines.launch

class LoginActivityVM(private val userDaoInstance: UserDao) : ViewModel() {

    var activeUserIdNumber by mutableStateOf<Long?>(null)
        private set

    fun SearchUserByInputData(login: String, pass: String) {
        if (login.isNotEmpty() && pass.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val userId = userDaoInstance.getUserId(
                        inputLoginData = login,
                        inputPasswordData = pass)
                    activeUserIdNumber = userId
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }


        println(userDaoInstance.getUserData(0))
        // println(userDaoInstance.getUsersData())

    }
}
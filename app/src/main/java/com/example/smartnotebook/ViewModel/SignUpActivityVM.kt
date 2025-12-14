package com.example.smartnotebook.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpActivityVM : ViewModel() {

    // Прошел ли проверку
    private var _isLoginAndPasswordExist by mutableStateOf<Boolean>(true)
    val isLoginAndPasswordExist: Boolean get() = _isLoginAndPasswordExist

    private var _newUserAccountId by mutableStateOf<Long?>(null)
    val newUserAccountId: Long? get() = _newUserAccountId

    // Статус ошибки
    private var _statusSignInError by mutableStateOf<String?>(null)
    val statusSignInError: String? get() = _statusSignInError
}
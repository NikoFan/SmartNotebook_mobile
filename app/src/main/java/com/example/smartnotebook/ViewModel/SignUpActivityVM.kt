package com.example.smartnotebook.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnotebook.Model.LoginRequest
import com.example.smartnotebook.Model.RegisterInitRequest
import com.example.smartnotebook.RetrofitActions.ApiFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _uiState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val uiState: StateFlow<RegistrationState> = _uiState

    fun startRegistration(login: String, password: String, email: String) {
        if (login.isEmpty() or password.isEmpty() or email.isEmpty()){
            _statusSignInError = "Заполните все поля!"
            return
        }
        if (!isValidCustomEmail(email)){
            _statusSignInError = "Проверьте почту!"
            return
        }
        viewModelScope.launch {
            _uiState.value = RegistrationState.Loading
            try {
                val request = RegisterInitRequest(
                    user_login = login,
                    user_password = password,
                    user_mail = email)
                val response = ApiFactory.apiService.initRegistration(request)

                if (response.isSuccessful) {
                    _uiState.value = RegistrationState.SuccessInit(email) // Передаём email на след. экран
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _uiState.value = RegistrationState.Error(error)
                }
            } catch (e: Exception) {
                _uiState.value = RegistrationState.Error(e.message ?: "Network error")
            }
        }
    }
}

sealed class RegistrationState {
    object Idle : RegistrationState()
    object Loading : RegistrationState()
    data class SuccessInit(val email: String) : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}
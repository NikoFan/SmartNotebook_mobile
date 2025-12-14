package com.example.smartnotebook.Model

data class RegisterRequest(
    val user_login: String,
    val user_password: String,
    val user_mail: String
)

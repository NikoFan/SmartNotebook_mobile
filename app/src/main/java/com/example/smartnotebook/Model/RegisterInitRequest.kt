package com.example.smartnotebook.Model

data class RegisterInitRequest(
    val user_login: String,
    val user_password: String,
    val user_mail: String
)

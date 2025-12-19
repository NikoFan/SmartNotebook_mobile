package com.example.smartnotebook.Model

data class RegisterConfirmRequest(
    val email: String,
    val code: String
)

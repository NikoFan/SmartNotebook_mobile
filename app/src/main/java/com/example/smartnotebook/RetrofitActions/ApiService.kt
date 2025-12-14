package com.example.smartnotebook.RetrofitActions

import com.example.smartnotebook.Model.LoginRequest
import com.example.smartnotebook.Model.RegisterRequest
import com.example.smartnotebook.Model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun register(@Body request: RegisterRequest) : Response<UserResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest) : Response<UserResponse>
}
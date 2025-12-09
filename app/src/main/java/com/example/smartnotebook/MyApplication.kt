package com.example.smartnotebook

import android.app.Application
import com.example.smartnotebook.Model.Room.AppDatabase

class MyApplication : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getInstance(this)
    }
}
package com.example.smartnotebook.Model.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smartnotebook.Model.Room.DAOs.RecordDao
import com.example.smartnotebook.Model.Room.DAOs.UserDao
import com.example.smartnotebook.Model.Room.Entities.RecordsEntity
import com.example.smartnotebook.Model.Room.Entities.UserEntity


@Database(
    entities = [
        UserEntity::class, // Таблица с пользователем
        RecordsEntity::class // Таблица с заметками
               ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun recordDao() : RecordDao


    companion object{
        @Volatile // Все потоки видят одно и тоже значение INSTANCE
        private var INSTANCE: AppDatabase? = null

        fun getInstance(
            context: Context
        ) : AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notebook_database4")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance // Созданный экземпляр сохраняем в INSTANCE
                instance
            }
        }
    }
}
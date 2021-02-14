package com.kiran.roomdatabase25c.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kiran.roomdatabase25c.dao.UserDAO
import com.kiran.roomdatabase25c.entity.User

@Database(
    entities = [(User::class)],
    version = 1
)
abstract class StudentDB : RoomDatabase() {

    abstract fun getUserDAO(): UserDAO
    abstract fun getUserDao(): Any

    companion object {
        @Volatile
        private var instance: StudentDB? = null
        fun getInstance(context: Context): StudentDB {
            if (instance == null) {
                synchronized(StudentDB::class) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }
        private fun buildDatabase(context: Context): StudentDB {
            return Room.databaseBuilder(
                context.applicationContext,
                StudentDB::class.java,
                "StudentDB"
            ).build()
        }
    }
}

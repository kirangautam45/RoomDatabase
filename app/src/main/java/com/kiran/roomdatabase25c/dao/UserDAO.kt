package com.kiran.roomdatabase25c.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kiran.roomdatabase25c.entity.User

@Dao
interface UserDAO {
    // insert user
    @Insert
    suspend fun registerUser(user : User)

    @Query("Select * from User where username=(:username) and password=(:password)")
    suspend fun loginUser(username:String,password:String):User
}
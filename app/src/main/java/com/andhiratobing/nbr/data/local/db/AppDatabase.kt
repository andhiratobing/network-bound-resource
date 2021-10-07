package com.andhiratobing.nbr.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andhiratobing.nbr.data.local.dao.UserDao
import com.andhiratobing.nbr.data.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
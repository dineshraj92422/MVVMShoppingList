package com.drmarks.shoppinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext
import java.util.concurrent.locks.Lock


@Database(
    entities = [ShoppingItem::class],
    version = 1  //Update the version every time you change anything in database.

)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun getShoppinDao(): ShoppingDao

    companion object{
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: createDatabase(context).also { instance = it }
        }
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,
            ShoppingDatabase::class.java,"ShoppingDB.db").build()
    }
}
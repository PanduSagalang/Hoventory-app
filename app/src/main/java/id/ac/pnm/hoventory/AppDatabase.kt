package id.ac.pnm.hoventory

import androidx.room.Database
import androidx.room.Room

import id.ac.pnm.hoventory.data.Product
import android.content.Context
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun ProductDao(): ProductDao

    companion object{
        var INSTANCE: AppDatabase?=null

        fun getInstance(applicationContext: Context): AppDatabase{
            return  INSTANCE?:Room.databaseBuilder(
            applicationContext,
                AppDatabase::class.java,"product_database"
            ).build().also{
                INSTANCE = it
            }
        }
    }
}
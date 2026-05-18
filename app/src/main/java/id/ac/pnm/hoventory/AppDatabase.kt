package id.ac.pnm.hoventory

import androidx.room.Database
import androidx.room.Room

import id.ac.pnm.hoventory.data.Product
import android.content.Context
import androidx.room.RoomDatabase
import id.ac.pnm.hoventory.data.Riwayat

@Database(entities = [Product::class, Riwayat::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun ProductDao(): ProductDao
    abstract fun RiwayatDao(): RiwayatDao

    companion object{
        @Volatile
        var INSTANCE: AppDatabase?=null

        fun getInstance(applicationContext: Context): AppDatabase{
            return  INSTANCE?:Room.databaseBuilder(
            applicationContext,
                AppDatabase::class.java,
                "product_database"
            )
                .fallbackToDestructiveMigration(true)
                .build().also{
                INSTANCE = it
            }
        }
    }
}
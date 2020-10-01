package com.egemeninceler.kutuphanem.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.egemeninceler.kutuphanem.data.local.dao.BookDao
import com.egemeninceler.kutuphanem.data.local.db.typeconverter.UriTypeConverter
import com.egemeninceler.kutuphanem.data.local.entity.Book


@Database(entities = [Book::class], version = 1, exportSchema = false)
@TypeConverters(UriTypeConverter::class)
abstract class BookRoomDatabase() : RoomDatabase() {
    abstract fun bookDao(): BookDao


    companion object {
        @Volatile
        private var INSTANCE: BookRoomDatabase? = null

        private val lock = Any()
        fun getDatabase(context: Context): BookRoomDatabase =
            INSTANCE ?: synchronized(lock) {
                INSTANCE ?: makeDataBase(context).also {
                    INSTANCE = it
                }
            }

        private fun makeDataBase(
            context: Context
        ) = Room.databaseBuilder(
            context.applicationContext,
            BookRoomDatabase::class.java,
            "countryDatabase"
        ).build()

    }

}
package com.egemeninceler.kutuphanem.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.egemeninceler.kutuphanem.data.local.dao.BookDao
import com.egemeninceler.kutuphanem.data.local.entity.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookRoomDatabase() : RoomDatabase() {
    abstract fun bookDao(): BookDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var wordDao = database.bookDao()

                    // Delete all content here.
                    //wordDao.deleteAll()

                    // Add sample words.
                    var word = Book("Hello")
                    wordDao.insert(word)
                    word = Book("World!")
                    wordDao.insert(word)

                    // TODO: Add your own words!
                    word = Book("TODO!")
                    wordDao.insert(word)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: BookRoomDatabase? = null

        private val lock = Any()
        fun getDatabase(context: Context, scope: CoroutineScope): BookRoomDatabase =
            INSTANCE ?: synchronized(lock) {
                INSTANCE ?: makeDataBase(context, scope).also {
                    INSTANCE = it
                }
            }

        private fun makeDataBase(
            context: Context, scope: CoroutineScope
        ) = Room.databaseBuilder(
            context.applicationContext,
            BookRoomDatabase::class.java,
            "countryDatabase"
        ).addCallback(WordDatabaseCallback(scope)).build()

    }

}
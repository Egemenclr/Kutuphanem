package com.egemeninceler.kutuphanem.data.repository

import androidx.lifecycle.LiveData
import com.egemeninceler.kutuphanem.data.local.dao.BookDao
import com.egemeninceler.kutuphanem.data.local.entity.Book

class BookRepository(private val bookDao: BookDao) {
    val allBooks: LiveData<List<Book>> = bookDao.getAllBook()

    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }
}
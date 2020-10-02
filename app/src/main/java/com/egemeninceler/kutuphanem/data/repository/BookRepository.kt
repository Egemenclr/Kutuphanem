package com.egemeninceler.kutuphanem.data.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import com.egemeninceler.kutuphanem.data.local.dao.BookDao
import com.egemeninceler.kutuphanem.data.local.entity.Book

class BookRepository(private val bookDao: BookDao) {
    val allBooks: LiveData<List<Book>> = bookDao.getAllBook()
    //val book = LiveData<Book> = bookDao.getBook()

    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }

    fun getBook(uuid: Int): Book = bookDao.getBook(uuid)

    suspend fun updateBook(imageName: String, imagePath: Uri, uuid: Int) =
        bookDao.updateBook(imageName, imagePath, uuid)
}
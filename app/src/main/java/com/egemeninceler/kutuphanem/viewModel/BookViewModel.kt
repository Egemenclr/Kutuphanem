package com.egemeninceler.kutuphanem.viewModel

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.egemeninceler.kutuphanem.data.local.db.BookRoomDatabase
import com.egemeninceler.kutuphanem.data.local.entity.Book
import com.egemeninceler.kutuphanem.data.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BookRepository

    val allBooks: LiveData<List<Book>>

    init {
        val dao = BookRoomDatabase.getDatabase(getApplication()).bookDao()
        repository = BookRepository(dao)
        allBooks = repository.allBooks
    }

    fun insert(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        Log.e("ViewModel", "${book.uuid}")

        repository.insert(book)
    }

    fun getBook(uuid: Int): Book {
        Log.e("ViewModel", "$uuid")
        return repository.getBook(uuid)
    }

    suspend fun updateBook(imageName: String, imagePath: Uri, uuid: Int) =
        repository.updateBook(imageName, imagePath, uuid)

    suspend fun deleteBook(uuid: Int) {
        repository.deleteBook(uuid)
    }

}
package com.egemeninceler.kutuphanem.viewModel

import android.app.Application
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
        val dao = BookRoomDatabase.getDatabase(getApplication(), viewModelScope).bookDao()
        repository = BookRepository(dao)
        allBooks = repository.allBooks
    }

    fun insert(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(book)
    }
}
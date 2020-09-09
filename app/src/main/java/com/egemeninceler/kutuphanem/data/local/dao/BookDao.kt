package com.egemeninceler.kutuphanem.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.egemeninceler.kutuphanem.data.local.entity.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM book_table")
    fun getAllBook(): LiveData<List<Book>>
    // suspend olmamasının sebebi ayrı thread'de değil direkt olarak görmemiz gerektiği için.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Query("DELETE FROM book_table")
    suspend fun deleteAll()
}
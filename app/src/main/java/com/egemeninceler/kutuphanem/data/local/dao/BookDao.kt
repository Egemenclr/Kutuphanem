package com.egemeninceler.kutuphanem.data.local.dao

import android.net.Uri
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

    @Query("SELECT * FROM book_table WHERE uuid =:uid")
    fun getBook(uid: Int): Book

    @Query("UPDATE book_table SET imageName=:imageName , imagePath=:imagePath WHERE uuid=:uuid ")
    suspend fun updateBook(imageName: String, imagePath: Uri, uuid: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Query("DELETE FROM book_table")
    suspend fun deleteAll()
}
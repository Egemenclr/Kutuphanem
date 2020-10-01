package com.egemeninceler.kutuphanem.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.data.local.db.BookRoomDatabase
import com.egemeninceler.kutuphanem.data.local.entity.Book
import com.egemeninceler.kutuphanem.viewModel.BookViewModel
import kotlinx.android.synthetic.main.activity_add_new_book.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddNewBookActivity : AppCompatActivity() {
    //var selectedPicture: Uri? = null
    private val storageRequsetCode = 2
    private val requestCodeForResult = 3
    private var IMAGE_URI: Uri? = null
    lateinit var bookViewModel: BookViewModel
    var abc: Book? = null

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_book)

        val dao = BookRoomDatabase.getDatabase(getApplication()).bookDao()

        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        val bookID = intent.getIntExtra("unuqueid", -1)
        if (bookID != -1) {
            GlobalScope.launch {
                abc = bookViewModel.getBook(bookID)
                IMAGE_URI = abc!!.pathImage
            }
        }

        newBookImg.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    storageRequsetCode
                )
            } else {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, requestCodeForResult)

            }
            //finish()
        }

        newBookSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(newBookName.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = newBookName.text.toString()
                val book = Book(word, IMAGE_URI)
                replyIntent.putExtra(BOOK_NAME, book)
                //replyIntent.putExtra("imageUri", IMAGE_URI)
                Log.e("AddNewBookActivity ", "$IMAGE_URI")
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        if (IMAGE_URI != null) {
            if (Build.VERSION.SDK_INT >= 28) {
                val source = ImageDecoder.createSource(contentResolver, IMAGE_URI!!)
                val bitmap = ImageDecoder.decodeBitmap(source)
                newBookImg.setImageBitmap(bitmap)
            } else {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(contentResolver, IMAGE_URI)
                newBookImg.setImageBitmap(bitmap)
            }

        } else {
            println()
        }

        newBookName.hint = abc!!.name
    }

    companion object {
        const val BOOK_NAME = "com.egemeninceler.kutuphanem.BOOKNAME"


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == storageRequsetCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, requestCodeForResult)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == requestCodeForResult && resultCode == Activity.RESULT_OK && data != null) {
            //selectedPicture = data.data
            IMAGE_URI = data.data
            if (IMAGE_URI != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    val source = ImageDecoder.createSource(this.contentResolver, IMAGE_URI!!)
                    val bitmap = ImageDecoder.decodeBitmap(source)
                    newBookImg.setImageBitmap(bitmap)
                } else {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, IMAGE_URI)
                    newBookImg.setImageBitmap(bitmap)
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}

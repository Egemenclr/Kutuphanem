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
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.data.local.entity.Book
import com.egemeninceler.kutuphanem.network.ApiClient
import com.egemeninceler.kutuphanem.network.ISBNService
import com.egemeninceler.kutuphanem.viewModel.BookViewModel
import kotlinx.android.synthetic.main.activity_add_new_book.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddNewBookActivity : AppCompatActivity() {

    private val storageRequsetCode = 2
    private val requestCodeForResult = 3
    private var IMAGE_URI: Uri? = null
    lateinit var bookViewModel: BookViewModel
    var oldBook: Book? = null
    var newBook = Book("", IMAGE_URI)
    lateinit var isbnService: ISBNService
//    lateinit var bookInfo: SearchResult


    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_book)

        val textView = findViewById<AutoCompleteTextView>(R.id.edt_name)
        val countries: Array<out String> = resources.getStringArray(R.array.countries_array)
        isbnService = ApiClient.getClient().create(ISBNService::class.java)


        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries).also { adapter ->
            textView.setAdapter(adapter)
        }

//        Thread.sleep(500L)
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)


        val bookID = intent.getIntExtra("unuqueid", -1)
        if (bookID != -1) {
            GlobalScope.launch {
                oldBook = bookViewModel.getBook(bookID)
                Log.e("globalscope", "${oldBook?.name}, ${oldBook?.pathImage}")
                IMAGE_URI = oldBook!!.pathImage
            }
        }

        //image ekleme
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


        // kayıt etme
        newBookSave.setOnClickListener {
            val replyIntent = Intent()
            if (bookID != -1) {
                newBook.name = edt_name.text.toString()
                newBook.pathImage = IMAGE_URI
                GlobalScope.launch {
                    if ((newBook.name != oldBook!!.name) && (newBook.pathImage != oldBook!!.pathImage))
                        bookViewModel.updateBook(newBook.name, newBook.pathImage!!, bookID)
                    else if (newBook.name != oldBook!!.name)
                        bookViewModel.updateBook(newBook.name, oldBook!!.pathImage!!, bookID)
                    else if (newBook.pathImage!! != oldBook!!.pathImage)
                        bookViewModel.updateBook(oldBook!!.name, newBook.pathImage!!, bookID)

                }
            }

            if (TextUtils.isEmpty(edt_name.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = edt_name.text.toString()
                val book = Book(word, IMAGE_URI)
                replyIntent.putExtra(BOOK_NAME, book)
                //replyIntent.putExtra("imageUri", IMAGE_URI)
                Log.e("AddNewBookActivity ", "$IMAGE_URI")
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

//        newBookISBN.setOnClickListener {
//            val isbn = edt_ISBN.text
//            var result = isbnService.resultGet(isbn.toString())
//
//            result.enqueue(object : Callback<SearchResult> {
//                override fun onResponse(
//                    call: Call<SearchResult>,
//                    response: Response<SearchResult>
//                ) {
//                    if (response.isSuccessful) {
//                        bookInfo = response.body()!!
//                    }
//                }
//
//                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
//                    Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG)
//                        .show()
//                }
//
//
//            })
//        }
    }

    override fun onStart() {
        super.onStart()
        Thread.sleep(500L)
        change()
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

    fun change() {
        if (IMAGE_URI != null && IMAGE_URI != Uri.parse("")) {

            if (Build.VERSION.SDK_INT >= 28) {
                val source = ImageDecoder.createSource(contentResolver, IMAGE_URI!!)
                val bitmap = ImageDecoder.decodeBitmap(source)
                newBookImg.setImageBitmap(bitmap)
            } else {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(contentResolver, IMAGE_URI)
                newBookImg.setImageBitmap(bitmap)
            }

        }
        Log.e("oldBook", "${oldBook?.name}, ${oldBook?.pathImage}")
        edt_name.setText(oldBook?.name)
    }
}

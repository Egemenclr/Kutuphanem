package com.egemeninceler.kutuphanem.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.egemeninceler.kutuphanem.R
import kotlinx.android.synthetic.main.activity_add_new_book.*

class AddNewBookActivity : AppCompatActivity() {
    var selectedPicture: Uri? = null
    private val storageRequsetCode = 2
    private val requestCodeForResult = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_book)

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
            finish()
        }

        newBookSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(newBookName.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = newBookName.text.toString()
                replyIntent.putExtra(BOOK_NAME, word)
                replyIntent.putExtra("imageUri",IMAGE_URI)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val BOOK_NAME = "com.egemeninceler.kutuphanem.BOOKNAME"
        var IMAGE_URI : Uri? = null
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
            selectedPicture = data.data
            IMAGE_URI = data.data
            if (selectedPicture != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    val source = ImageDecoder.createSource(this.contentResolver, selectedPicture!!)
                    val bitmap = ImageDecoder.decodeBitmap(source)
                    newBookImg.setImageBitmap(bitmap)
                } else {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, selectedPicture)
                    newBookImg.setImageBitmap(bitmap)
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}

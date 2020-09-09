package com.egemeninceler.kutuphanem.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.egemeninceler.kutuphanem.ui.MainActivity

fun <T>AppCompatActivity.extStartActivity(cls : Class<T>){
    val intent = Intent(this, cls)
    startActivity(intent)
}
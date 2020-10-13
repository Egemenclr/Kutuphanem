package com.egemeninceler.kutuphanem.util

import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.enums.FragmentName
import com.egemeninceler.kutuphanem.model.FragmentModel
import com.egemeninceler.kutuphanem.ui.BooksFragment
import com.egemeninceler.kutuphanem.ui.silinecekFragment
import kotlinx.android.synthetic.main.pop_up_layout.view.*

fun <T> AppCompatActivity.extStartActivity(cls: Class<T>) {
    val intent = Intent(this, cls)
    startActivity(intent)
}

fun getFragmentList(): ArrayList<FragmentModel> {
    val fragmentList = ArrayList<FragmentModel>()

    val booksFragment = BooksFragment()
    val booksFragment2 = silinecekFragment()

    val fragmentModel = FragmentModel(FragmentName.KUTUPHANEM.toString(), booksFragment)
    val fragmentModel3 =
        FragmentModel(FragmentName.RAHATLATICI_SESLER.toString(), booksFragment2)

    fragmentList.add(fragmentModel)
    fragmentList.add(fragmentModel3) // ucuncu fragment eklenecek.

    return fragmentList
}


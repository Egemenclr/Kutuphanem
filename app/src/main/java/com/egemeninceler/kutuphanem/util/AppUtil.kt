package com.egemeninceler.kutuphanem.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.egemeninceler.kutuphanem.enums.FragmentName
import com.egemeninceler.kutuphanem.model.FragmentModel
import com.egemeninceler.kutuphanem.ui.BooksFragment
import com.egemeninceler.kutuphanem.ui.FindBookFragment

fun <T> AppCompatActivity.extStartActivity(cls: Class<T>) {
    val intent = Intent(this, cls)
    startActivity(intent)
}

fun getFragmentList(): ArrayList<FragmentModel> {
    val fragmentList = ArrayList<FragmentModel>()

    val booksFragment = BooksFragment()
    val findBookFragment = FindBookFragment()
    val findBookFragment2 = FindBookFragment()

    val fragmentModel = FragmentModel(FragmentName.KUTUPHANEM.toString(), booksFragment)
    val fragmentModel2 = FragmentModel(FragmentName.KITAP_BUL.toString(), findBookFragment)
    val fragmentModel3 =
        FragmentModel(FragmentName.RAHATLATICI_SESLER.toString(), findBookFragment2)
    fragmentList.add(fragmentModel)
    fragmentList.add(fragmentModel2)
    fragmentList.add(fragmentModel3) // ucuncu fragment eklenecek.

    return fragmentList
}
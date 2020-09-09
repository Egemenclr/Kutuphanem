package com.egemeninceler.kutuphanem.mock

import com.egemeninceler.kutuphanem.enums.FragmentName
import com.egemeninceler.kutuphanem.model.FragmentModel
import com.egemeninceler.kutuphanem.ui.BooksFragment
import com.egemeninceler.kutuphanem.ui.FindBookFragment

object MockData {
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

    private fun getTitleList(positon: Int): String {
        val titleList: String = when (positon) {
            0 -> "KUTUPHANEM"
            1 -> "KITAP BUL"
            2 -> "RAHATLATICI SESLER"
            else -> ""
        }
        return titleList
    }


//    fun getBookList(size: Int): ArrayList<String> {
//        val bookList = ArrayList<String>()
//        repeat(size) {
//            val bookName = "En Güzel Eylül"
//            bookList.add(bookName)
//        }
//
//        return bookList
//    }
}
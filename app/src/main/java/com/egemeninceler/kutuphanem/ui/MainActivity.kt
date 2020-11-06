package com.egemeninceler.kutuphanem.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.adapter.MainPagerAdapter
import com.egemeninceler.kutuphanem.data.local.entity.Book
import com.egemeninceler.kutuphanem.model.AutoBookModel
import com.egemeninceler.kutuphanem.util.getFragmentList
import com.egemeninceler.kutuphanem.viewModel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var bookViewModel: BookViewModel
    var books: List<Book> = arrayListOf()
    var autoBooks: ArrayList<AutoBookModel> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        bookViewModel.allBooks.observe(this, Observer {
            it?.let {
                books = it
                println("----- ${books.size}")
                for (i in it) {
                    var autoBook = AutoBookModel(i.name, R.drawable.ic_auto_book)
                    autoBooks.add(autoBook)
                }
            }
        })

        mainPager.adapter = MainPagerAdapter(supportFragmentManager, getFragmentList())

        tabNavBar.setupWithViewPager(mainPager)
        val fragmentList =
            mutableListOf<Int>(R.drawable.ic_fragment_book, R.drawable.ic_fragment_music)

        for (i in fragmentList) {
            tabNavBar.getTabAt(fragmentList.indexOf(i))!!.setIcon(i)
        }

        toolbar.inflateMenu(R.menu.fragment_menu)
        toolbar.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
            Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                return when (item.itemId) {
                    R.id.menu_search -> {
                        startActivity(Intent(this@MainActivity, SearchBookActivity::class.java))
                        true
                    }
                    else -> false
                }
            }
        })


    }

}


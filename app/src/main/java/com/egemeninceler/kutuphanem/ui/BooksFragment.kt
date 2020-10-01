package com.egemeninceler.kutuphanem.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.adapter.BookFragmentAdapter
import com.egemeninceler.kutuphanem.data.local.entity.Book
import com.egemeninceler.kutuphanem.viewModel.BookViewModel
import kotlinx.android.synthetic.main.fragment_books.*


class BooksFragment : Fragment() {

    private val adapter = BookFragmentAdapter(arrayListOf()) {
        val intent = Intent(activity, AddNewBookActivity::class.java)
        intent.putExtra("unuqueid", it.uuid)
        //intent.putExtra("where","old")
        Thread.sleep(250L)
        startActivity(intent)
    }
    private val requestCodeForResult = 1
    private lateinit var wordViewModel: BookViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerBook.layoutManager = GridLayoutManager(view.context, 3)
        recyclerBook.adapter = adapter



        wordViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        wordViewModel.allBooks.observe(viewLifecycleOwner, Observer { books ->
            books?.let {
                adapter.setValues(it)
            }
        })

        addNewBook.setOnClickListener {
            val intent = Intent(activity, AddNewBookActivity::class.java)
            //intent.putExtra("where","new")
            startActivityForResult(intent, requestCodeForResult)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestCodeForResult && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Book>(AddNewBookActivity.BOOK_NAME).let {
                val book = it

                wordViewModel.insert(book!!)
            }

        } else {
            Toast.makeText(activity, "damnt", Toast.LENGTH_SHORT).show()
        }
    }
}
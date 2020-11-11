package com.egemeninceler.kutuphanem.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.adapter.BookFragmentAdapter
import com.egemeninceler.kutuphanem.data.local.entity.Book
import com.egemeninceler.kutuphanem.viewModel.BookViewModel
import kotlinx.android.synthetic.main.fragment_books.*


class BooksFragment : Fragment() {

    private val adapter = BookFragmentAdapter(arrayListOf(), {
        val intent = Intent(activity, AddNewBookActivity::class.java)
        intent.putExtra("unuqueid", it.uuid)
        //intent.putExtra("where","old")

        startActivity(intent)

    }, {

        val popUpClass = PopUpClass()
        popUpClass.showPopUpWindow(view!!, it.uuid, this)


    })
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

        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL).apply {
            recyclerBook.layoutManager = this

        }
        recyclerBook.adapter = adapter


        wordViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        wordViewModel.allBooks.observe(viewLifecycleOwner, Observer { books ->
            books?.let {
                adapter.setValues(it)

            }
        })

        val anim = ScaleAnimation(0F, 1F, 0F, 1F)
        anim.fillBefore = true
        anim.fillAfter = true
        anim.isFillEnabled = true
        anim.duration = 50
        anim.interpolator = OvershootInterpolator()
        addNewBook.setOnClickListener {
            val intent = Intent(activity, AddNewBookActivity::class.java)

            addNewBook.startAnimation(anim)
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

        }
    }

    override fun onResume() {
        super.onResume()
        book_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
        recyclerBook.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val transition: Transition = Slide(Gravity.BOTTOM)
                TransitionManager.beginDelayedTransition(book_searchView, transition)

                if (dy > 0) {
                    book_searchView.visibility = View.VISIBLE
                    println("---- $dy")

                } else {
                    if (dy == 0) return
                    else book_searchView.visibility = View.GONE

                }
            }
        })
    }


}
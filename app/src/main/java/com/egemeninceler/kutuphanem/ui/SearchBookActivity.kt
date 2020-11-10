package com.egemeninceler.kutuphanem.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.adapter.SearchBookAdapter
import com.egemeninceler.kutuphanem.adapter.SearchBookResAdapter
import com.egemeninceler.kutuphanem.model.newBookModel.AllBooksResponse
import com.egemeninceler.kutuphanem.model.seachBookModel.SearchResult
import com.egemeninceler.kutuphanem.network.ApiClient
import com.egemeninceler.kutuphanem.network.ISBNService
import kotlinx.android.synthetic.main.activity_search_book.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchBookActivity : AppCompatActivity() {
    lateinit var isbnService: ISBNService
    var titleOfBooks = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)




        isbnService = ApiClient.getClient().create(ISBNService::class.java)
        isbnService.getAllBooks().enqueue(object : Callback<AllBooksResponse> {
            override fun onResponse(
                call: Call<AllBooksResponse>,
                response: Response<AllBooksResponse>
            ) {
                if (response.isSuccessful) {
                    val res: AllBooksResponse = response.body()!!
                    val listOfBooks = res.result
                    for (i in listOfBooks) {
                        titleOfBooks.add(i.title)
                    }

                    Thread.sleep(100L)

                    val adapter = SearchBookAdapter(titleOfBooks) {
                        Toast.makeText(applicationContext, "helü", Toast.LENGTH_SHORT).show()
                    }
                    searchRecyclerview.layoutManager =
                        LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
                    searchRecyclerview.adapter = adapter

                    progress_circular.visibility = View.GONE
                    println("*** $listOfBooks")
                    println("*** $titleOfBooks")
                }
            }

            override fun onFailure(call: Call<AllBooksResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

        btn_search.setOnClickListener {
            progress_circular.visibility = View.VISIBLE
            isbnService.resultGet(edt_searchbook.text.toString())
                .enqueue(object : Callback<SearchResult> {
                    override fun onResponse(
                        call: Call<SearchResult>,
                        response: Response<SearchResult>
                    ) {
                        if (response.isSuccessful) {
                            val searchBook = response.body()!!.result
                            println("** $searchBook")

                            val adapter2 = SearchBookResAdapter(searchBook) {
                                println("*** ${it.url}")
                                Toast.makeText(applicationContext, "helü}", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            StaggeredGridLayoutManager(
                                3,
                                StaggeredGridLayoutManager.VERTICAL
                            ).apply {
                                searchRecyclerview.layoutManager = this
                            }
                            searchRecyclerview.adapter = adapter2

                            progress_circular.visibility = View.GONE
                            txt_search_popular.visibility = View.GONE
                        }
                    }

                    override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                        Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT)
                            .show()
                    }

                })
        }

    }
}
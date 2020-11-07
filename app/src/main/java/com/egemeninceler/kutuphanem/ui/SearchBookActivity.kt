package com.egemeninceler.kutuphanem.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.adapter.SearchBookAdapter
import com.egemeninceler.kutuphanem.model.newBookModel.AllBooksResponse
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

                    val adapter = SearchBookAdapter(titleOfBooks)
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

    }
}
package com.egemeninceler.kutuphanem.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.adapter.SearchBookAdapter
import com.egemeninceler.kutuphanem.model.AllBooksResponse
import com.egemeninceler.kutuphanem.network.ApiClient
import com.egemeninceler.kutuphanem.network.ISBNService
import kotlinx.android.synthetic.main.activity_search_book.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchBookActivity : AppCompatActivity() {
    lateinit var isbnService: ISBNService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)

        val adapter = SearchBookAdapter(arrayListOf())

        isbnService = ApiClient.getClient().create(ISBNService::class.java)
        isbnService.getAllBooks().enqueue(object : Callback<AllBooksResponse> {
            override fun onResponse(
                call: Call<AllBooksResponse>,
                response: Response<AllBooksResponse>
            ) {
                if (response.isSuccessful) {
                    val res: AllBooksResponse = response.body()!!
                    println("*** $res")
                }
            }

            override fun onFailure(call: Call<AllBooksResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })


        searchRecyclerview.adapter = adapter

    }
}
package com.egemeninceler.kutuphanem.network


import com.egemeninceler.kutuphanem.model.AllBooksResponse
import com.egemeninceler.kutuphanem.model.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ISBNService {
    @Headers(
        "content-type: application/json",
        "authorization: apikey 1OhmtJpXim8ljlRAymmEaj:1hwic5yNKi41QtDAVpQilV"
    )
    @GET("book/search/")
//    @GET("book/search?data.query=" )
    fun resultGet(@Query("data.query") bookName: String): Call<SearchResult>

    @Headers(
        "content-type: application/json",
        "authorization: apikey 1OhmtJpXim8ljlRAymmEaj:1hwic5yNKi41QtDAVpQilV"
    )
    @GET("book/newBook")
    fun getAllBooks(): Call<AllBooksResponse>

}
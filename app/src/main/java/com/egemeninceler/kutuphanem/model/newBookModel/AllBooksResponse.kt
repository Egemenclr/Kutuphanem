package com.egemeninceler.kutuphanem.model.newBookModel

import com.google.gson.annotations.SerializedName

data class AllBooksResponse(
    @SerializedName("success")
    val success_: Boolean,
    @SerializedName("result")
    val result: List<ResponseBooks>



)
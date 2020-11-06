package com.egemeninceler.kutuphanem.model

import com.google.gson.annotations.SerializedName

data class AllBooksResponse(
    @SerializedName("success")
    val success_: Boolean,
    @SerializedName("result")
    val result: List<ResponseBooks>



)
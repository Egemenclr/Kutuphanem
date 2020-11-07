package com.egemeninceler.kutuphanem.model.seachBookModel

import com.egemeninceler.kutuphanem.model.seachBookModel.ResponseBookModel
import com.google.gson.annotations.SerializedName



data class SearchResult(
    @SerializedName("success")
    val success_: Boolean,
    @SerializedName("result")
    val result: List<ResponseBookModel>

)
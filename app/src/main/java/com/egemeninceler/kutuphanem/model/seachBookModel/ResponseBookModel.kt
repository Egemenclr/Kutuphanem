package com.egemeninceler.kutuphanem.model.seachBookModel


/**
 * SEARCHBOOK RESPONSE
 */
data class ResponseBookModel(
    val name: String,
    val url: String,
    val img: String,
    val oldPrice: String,
    val price: String,
    val author: String
)
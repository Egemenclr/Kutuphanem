package com.egemeninceler.kutuphanem.model.newBookModel


/**
 * NEWBOOK RESPONSE

 */
data class ResponseBooks(
    val url: String,
    val indirim: String,
    val fiyat: String,
    val yayin: String,
    val yazar: String,
    val title: String,
    val image: String
)
package com.egemeninceler.kutuphanem.model

data class ResponseBooks(
    val url: String,
    val indirim: String,
    val fiyat: String,
    val yayin: String,
    val yazar: String,
    val title: String,
    val image: String
)
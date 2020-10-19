package com.egemeninceler.kutuphanem.model

data class AutoBookModel(
    val name: String,
    val bookImage: Int
){
    override fun toString(): String {
        return name
    }
}
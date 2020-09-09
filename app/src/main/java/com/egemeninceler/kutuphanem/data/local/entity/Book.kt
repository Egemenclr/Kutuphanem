package com.egemeninceler.kutuphanem.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey val name: String
    //,var image: ByteArray? = null

)
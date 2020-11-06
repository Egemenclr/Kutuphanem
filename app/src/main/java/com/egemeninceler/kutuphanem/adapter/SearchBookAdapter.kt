package com.egemeninceler.kutuphanem.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egemeninceler.kutuphanem.data.local.entity.Book


class SearchBookAdapter(private val bookList: ArrayList<Book>) :
    RecyclerView.Adapter<SearchBookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBookViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SearchBookViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int = bookList.size

}
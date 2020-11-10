package com.egemeninceler.kutuphanem.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class SearchBookAdapter(
    private val bookList: ArrayList<String>,
    private val setOnClickListener: () -> Unit
) :
    RecyclerView.Adapter<SearchBookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBookViewHolder {
        return SearchBookViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SearchBookViewHolder, position: Int) {
        holder.bind(bookList[position], setOnClickListener)
    }

    override fun getItemCount(): Int = bookList.size
}
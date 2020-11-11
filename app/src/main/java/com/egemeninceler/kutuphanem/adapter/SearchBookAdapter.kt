package com.egemeninceler.kutuphanem.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egemeninceler.kutuphanem.model.newBookModel.ResponseBooks


class SearchBookAdapter(
    private val bookList: List<ResponseBooks>,
    private val setOnClickListener: (url: String) -> Unit
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
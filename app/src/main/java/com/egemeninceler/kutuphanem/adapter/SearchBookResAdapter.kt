package com.egemeninceler.kutuphanem.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egemeninceler.kutuphanem.model.seachBookModel.ResponseBookModel

class SearchBookResAdapter(private val resBook: List<ResponseBookModel>) :
    RecyclerView.Adapter<SearchBookResViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBookResViewHolder {
        return SearchBookResViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SearchBookResViewHolder, position: Int) {
        holder.bind(resBook[position])
    }

    override fun getItemCount(): Int {
        return resBook.size
    }

}
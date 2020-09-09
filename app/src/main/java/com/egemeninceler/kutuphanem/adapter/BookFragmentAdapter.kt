package com.egemeninceler.kutuphanem.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egemeninceler.kutuphanem.data.local.entity.Book

class BookFragmentAdapter(private val bookList: ArrayList<Book>) :
    RecyclerView.Adapter<BookFragmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookFragmentViewHolder {
        return BookFragmentViewHolder(parent)
    }

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: BookFragmentViewHolder, position: Int) {
        holder.bind(bookList[position].name)
    }

    fun setValues(books : List<Book>){
        bookList.clear()
        bookList.addAll(books)
        notifyDataSetChanged()
    }

}
package com.egemeninceler.kutuphanem.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.data.local.entity.Book
import kotlinx.android.synthetic.main.adapter_item_book.view.*


class BookFragmentViewHolder(container: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(container.context).inflate(
        R.layout.adapter_item_book,
        container,
        false
    )
) {
    fun bind(book: Book) {
        Log.e("ViewHolder", "${book.pathImage!!.path}")
        Glide.with(itemView.context)
            .load(book.pathImage)
            //.apply(RequestOptions().override(120, 120))
            .centerCrop()
            .into(itemView.imgBook)

        itemView.txtBookAdapter.text = book.name
    }


    /**
     *  xml --> viewholder --> adapter --> model daha doğru gibi
     *  xml --> viewholder --> model --> adapter ??
     */

}
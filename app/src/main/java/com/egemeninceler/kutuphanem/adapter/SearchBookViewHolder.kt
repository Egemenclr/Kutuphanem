package com.egemeninceler.kutuphanem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egemeninceler.kutuphanem.R
import kotlinx.android.synthetic.main.adapter_item_searchbook.view.*

class SearchBookViewHolder(container: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(container.context).inflate(
            R.layout.adapter_item_searchbook,
            container,
            false
        )
    ) {
    fun bind(book: String) {
//        Log.e("SearchBookViewHolder", "${book.pathImage!!.path}")
//        Glide.with(itemView.context)
//            .load(book.pathImage)
//            //.apply(RequestOptions().override(120, 120))
//            .centerCrop()
//            .into(itemView.imgBook)

        itemView.txt_searchbook.text = book
    }
}
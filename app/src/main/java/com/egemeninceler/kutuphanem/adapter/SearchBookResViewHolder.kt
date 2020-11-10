package com.egemeninceler.kutuphanem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.model.seachBookModel.ResponseBookModel
import kotlinx.android.synthetic.main.adapter_item_book.view.*

class SearchBookResViewHolder(container: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(container.context)
            .inflate(R.layout.adapter_item_book, container, false)
    ) {
    fun bind(resBook: ResponseBookModel, setOnClickListener: (book: ResponseBookModel) -> Unit) {
        Glide.with(itemView.context)
            .load(resBook.img)
            //.apply(RequestOptions().override(120, 120))
            .centerCrop()
            .into(itemView.imgBook)

//        itemView.txtBookAdapter.text = resBook.name
        itemView.txtBookAdapter.visibility = ViewGroup.GONE


        itemView.setOnClickListener {
            setOnClickListener(resBook)

        }
    }


}
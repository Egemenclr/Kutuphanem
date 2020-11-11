package com.egemeninceler.kutuphanem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.model.newBookModel.ResponseBooks
import kotlinx.android.synthetic.main.adapter_item_searchbook.view.*

class SearchBookViewHolder(container: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(container.context).inflate(
            R.layout.adapter_item_searchbook,
            container,
            false
        )
    ) {
    fun bind(book: ResponseBooks, setOnClickListener: (url: String) -> Unit) {
//        Log.e("SearchBookViewHolder", "${book.pathImage!!.path}")

//        Glide.with(itemView.context)
//            .load(book.image)
//            //.apply(RequestOptions().override(120, 120))
//            .centerCrop()
//            .into(itemView.img_searchbook)

        itemView.txt_searchbook_name.text = book.title
        itemView.txt_searchbook_author.text = "Yazar: " + book.yazar


        itemView.setOnClickListener {
            setOnClickListener(book.url)
        }
    }

}
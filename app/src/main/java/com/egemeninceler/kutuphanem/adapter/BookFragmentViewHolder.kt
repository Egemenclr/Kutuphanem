package com.egemeninceler.kutuphanem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egemeninceler.kutuphanem.R
import kotlinx.android.synthetic.main.adapter_item_book.view.*


class BookFragmentViewHolder(container: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(container.context).inflate(
        R.layout.adapter_item_book,
        container,
        false
    )
) {
    fun bind(s: String) {
        Glide.with(itemView.context)
            .load("https://i.dr.com.tr/cache/500x400-0/originals/0000000246061-1.jpg")
            .into(itemView.imgBook)



        itemView.txtBookAdapter.text = s

        //itemView.txtCategoryName.background = ContextCompat.getDrawable(itemView.context, model.background)
    }


    /**
     *  xml --> viewholder --> adapter --> model daha doğru gibi
     *  xml --> viewholder --> model --> adapter ??
     */

}
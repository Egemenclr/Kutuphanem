package com.egemeninceler.kutuphanem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.model.AutoBookModel

class AutoCompleteBookAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val allBooks: List<AutoBookModel>
) : ArrayAdapter<AutoBookModel>(context, layoutResource, allBooks),
    Filterable {

    private var mBooks : List<AutoBookModel> = allBooks

    override fun getCount(): Int {
        return mBooks.size
    }

    override fun getItem(position: Int): AutoBookModel? {
        return mBooks[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val ll = LayoutInflater.from(context).inflate(layoutResource,parent,false)
        val view = ll.findViewById<TextView>(R.id.autoText)
        view.text = mBooks[position].name

        var bookImage = ll.findViewById<ImageView>(R.id.autoImage)
        bookImage.setImageResource(mBooks[position].bookImage)


        return ll
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                mBooks = filterResults.values as List<AutoBookModel>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    allBooks
                else
                    allBooks.filter {
                        it.name.toLowerCase().contains(queryString)
                    }
                return filterResults
            }
        }
    }




}

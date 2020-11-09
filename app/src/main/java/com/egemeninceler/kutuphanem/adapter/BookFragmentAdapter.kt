package com.egemeninceler.kutuphanem.adapter

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.egemeninceler.kutuphanem.data.local.entity.Book
import java.util.*
import kotlin.collections.ArrayList

class BookFragmentAdapter(
    private var bookList: ArrayList<Book>,
    private val setOnClickListener: (book: Book) -> Unit,
    private val setOnLongClickListener: (book: Book) -> Unit

) :
    RecyclerView.Adapter<BookFragmentViewHolder>(), Filterable {
    var filteredList: ArrayList<Book>

    init {
        filteredList = bookList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookFragmentViewHolder {
        println("*** $viewType")
        return BookFragmentViewHolder(parent)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: BookFragmentViewHolder, position: Int) {
        holder.bind(filteredList[position], setOnClickListener, setOnLongClickListener)
    }

    fun setValues(books: List<Book>) {
        filteredList.clear()
        filteredList.addAll(books)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filteredList = bookList
                } else {
                    var resultList = ArrayList<Book>()
                    for (row in bookList) {
                        if (row.name.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    filteredList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<Book>
                notifyDataSetChanged()

            }

        }
    }


}
package com.egemeninceler.kutuphanem.ui

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.viewModel.BookViewModel
import kotlinx.android.synthetic.main.pop_up_layout.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PopUpClass {

    private lateinit var wordViewModel: BookViewModel
    fun showPopUpWindow(view: View, uuid: Int, owner: ViewModelStoreOwner) {

        wordViewModel = ViewModelProvider(owner).get(BookViewModel::class.java)

        val viewPopUp = LayoutInflater.from(view.context).inflate(R.layout.pop_up_layout, null)
        val popupWindow = PopupWindow(
            viewPopUp,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            true
        )


        viewPopUp.popUpDismiss.setOnClickListener {
            popupWindow.dismiss()
        }
        viewPopUp.popUpDelete.setOnClickListener {
            GlobalScope.launch {
                wordViewModel.deleteBook(uuid)

            }
            popupWindow.dismiss()

        }

        popupWindow.showAtLocation(viewPopUp, Gravity.CENTER, 0, 0)

    }
}
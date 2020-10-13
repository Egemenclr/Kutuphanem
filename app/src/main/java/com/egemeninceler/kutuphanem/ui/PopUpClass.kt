package com.egemeninceler.kutuphanem.ui

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import com.egemeninceler.kutuphanem.R
import kotlinx.android.synthetic.main.pop_up_layout.view.*

class PopUpClass {


    fun showPopUpWindow(view: View) {

        val view = LayoutInflater.from(view.context).inflate(R.layout.pop_up_layout, null)

        val focusable = true

        val popupWindow = PopupWindow(
            view,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            focusable
        )

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

        val test1 = view.findViewById<TextView>(R.id.titleText)

        view.messageButton.setOnClickListener {
            popupWindow.dismiss()
        }
    }


}
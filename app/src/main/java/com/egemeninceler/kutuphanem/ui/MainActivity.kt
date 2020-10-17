package com.egemeninceler.kutuphanem.ui

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.adapter.MainPagerAdapter
import com.egemeninceler.kutuphanem.util.getFragmentList
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPager.adapter = MainPagerAdapter(supportFragmentManager, getFragmentList())

        tabNavBar.setupWithViewPager(mainPager)
        val fragmentList =
            mutableListOf<Int>(R.drawable.ic_fragment_book, R.drawable.ic_fragment_music)

        for (i in fragmentList) {
            tabNavBar.getTabAt(fragmentList.indexOf(i))!!.setIcon(i)

        }


        toolbar.inflateMenu(R.menu.fragment_menu)
        toolbar.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
            Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                return when (item.itemId) {
                    R.id.menu_search -> {
//                        logo_toolbar.visibility= View.GONE
//                        edittext_toolbar.visibility = View.VISIBLE


                        val transition: Transition = Slide(Gravity.BOTTOM)
                        TransitionManager.beginDelayedTransition(toolbar, transition)

                        logo_toolbar.visibility = View.GONE
                        edittext_toolbar.visibility = View.VISIBLE
                        true
                    }
                    R.id.menu_logout -> {
                        true
                    }

                    else -> false
                }
            }
        })

    }


}


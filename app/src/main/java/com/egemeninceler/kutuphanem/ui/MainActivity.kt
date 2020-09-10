package com.egemeninceler.kutuphanem.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    }
}
package com.egemeninceler.kutuphanem.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.adapter.MainPagerAdapter
import com.egemeninceler.kutuphanem.mock.MockData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPager.adapter = MainPagerAdapter(supportFragmentManager, MockData.getFragmentList())

        tabNavBar.setupWithViewPager(mainPager)

//        val icon = ImageView(this)
//        icon.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.china))
//
//        val layoutParams = FloatingActionButton.LayoutParams(100, 100, 17)
//        val actionButton = FloatingActionButton.Builder(this).setContentView(icon, layoutParams).build()
//
//        val itemBuilder = SubActionButton.Builder(this)
//
//        val itemIcon1 = ImageView(this)
//        itemIcon1.setImageDrawable(
//            ContextCompat.getDrawable(
//                applicationContext,
//                R.drawable.add_button
//            )
//        )
    }


}
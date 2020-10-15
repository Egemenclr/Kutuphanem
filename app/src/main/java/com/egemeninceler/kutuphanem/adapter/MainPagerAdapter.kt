package com.egemeninceler.kutuphanem.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.egemeninceler.kutuphanem.model.FragmentModel

class MainPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragmentList: List<FragmentModel>
) :
    FragmentStatePagerAdapter(
        fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    override fun getItem(position: Int): Fragment {
        return fragmentList[position].fragment
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentList[position].title
    }


}
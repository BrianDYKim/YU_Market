package com.example.myapplication23.widget.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication23.screen.home.homelist.HomeListFragment

class HomeListFragmentPagerAdapter(
    fragment: Fragment,
    private val fragmentList: List<HomeListFragment>,
    // TODO add latlngEntity
) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}
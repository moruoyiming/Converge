package com.jzyc.instock.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class StrollPageAdapter : FragmentStateAdapter {

    private var fragments: List<Fragment>

    constructor(fragmentActivity: FragmentActivity, fragments: MutableList<out Fragment>) : super(fragmentActivity) {
        this.fragments = fragments
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

//    override fun containsItem(itemId: Long): Boolean {
//        return itemId in 0 until itemCount
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
}
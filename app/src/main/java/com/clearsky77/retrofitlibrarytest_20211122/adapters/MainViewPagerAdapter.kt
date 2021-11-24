package com.clearsky77.retrofitlibrarytest_20211122.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.clearsky77.retrofitlibrarytest_20211122.fragments.MyProfileFragment
import com.clearsky77.retrofitlibrarytest_20211122.fragments.ProductListFragment
import com.clearsky77.retrofitlibrarytest_20211122.fragments.ReviewListFragment

class MainViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
//    override fun getCount(): Int {
//        return 3
//    }
//  아래와 같이 줄일 수 있다!
    override fun getCount() = 3


    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ReviewListFragment()
            1 -> ProductListFragment()
            else -> MyProfileFragment()
        }
    }


}
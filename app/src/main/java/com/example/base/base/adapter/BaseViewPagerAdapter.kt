package com.example.base.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.base.base.BaseFragment

open class BaseViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private lateinit var listFragment: ArrayList<BaseFragment<out ViewDataBinding>>
    fun init(listFragment: ArrayList<BaseFragment<out ViewDataBinding>>) {
        this.listFragment = listFragment
    }

    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }
}
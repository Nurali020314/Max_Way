package com.skipissue.maxway.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.skipissue.maxway.presentation.screens.basket.BasketFragment
import com.skipissue.maxway.presentation.screens.mainfragment.MainFragment
import com.skipissue.maxway.presentation.screens.order.OrderFragment
import com.skipissue.maxway.presentation.screens.profil.ProfileFragment

class MyViewAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
       return 4
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0->{
               MainFragment()
           }
           1->{
               BasketFragment()
           }
           2->{
               OrderFragment()
           }else->{
               ProfileFragment()
           }

       }
    }

}
package com.skipissue.maxway.presentation.screens.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.presentation.adapter.MyViewAdapter
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var adatper: MyViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_main)


        adatper= MyViewAdapter(supportFragmentManager,lifecycle)
        binding.pager.adapter=adatper
        binding.pager.isUserInputEnabled=false
        binding.pager.offscreenPageLimit=4


        binding.bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main -> {
                    binding.pager.setCurrentItem(0,false)
                   // supportFragmentManager.beginTransaction().setReorderingAllowed(true).replace(
                   //     R.id.container, MainFragment()
                   // ).commit()
                }

                R.id.basket -> {
                    binding.pager.setCurrentItem(1,false)

                   // supportFragmentManager.beginTransaction().setReorderingAllowed(true).replace(
                   //     R.id.container, BasketFragment()
                   // ).commit()
                }

                R.id.my_orders -> {
                    binding.pager.setCurrentItem(2,false)

                  //  supportFragmentManager.beginTransaction().setReorderingAllowed(true).replace(
                  //      R.id.container, OrderFragment()
                  //  ).commit()
                }

                R.id.profile -> {
                    binding.pager.setCurrentItem(3,false)

                  // supportFragmentManager.beginTransaction().setReorderingAllowed(true).replace(
                  //     R.id.container, ProfileFragment()
                  // ).commit()
                }
            }
            return@setOnItemSelectedListener true
        }
       binding.pager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
           override fun onPageSelected(position: Int) {
               super.onPageSelected(position)
               val meneItem=binding.bottom.menu.getItem(position)
           }

       })
    }
}
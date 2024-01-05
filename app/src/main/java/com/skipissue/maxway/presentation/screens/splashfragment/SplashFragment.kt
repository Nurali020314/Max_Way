package com.skipissue.maxway.presentation.screens.splashfragment

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.skipissue.maxway.R
import com.skipissue.maxway.presentation.screens.lenguagefragment.LanguageFragment
import com.skipissue.maxway.presentation.screens.mainfragment.MainFragment
import com.skipissue.maxway.presentation.screens.mainfragment.ProductsViewModel

class SplashFragment : Fragment(R.layout.splash_fragment) {



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor = resources.getColor(R.color.primary, null)


        object : CountDownTimer(1000, 1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                parentFragmentManager.beginTransaction().replace(R.id.container, LanguageFragment()).commit()
            }
        }.start()
    }
}
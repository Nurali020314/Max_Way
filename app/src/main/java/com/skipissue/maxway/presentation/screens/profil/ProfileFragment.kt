package com.skipissue.maxway.presentation.screens.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.DetailFragmentBinding
import com.skipissue.maxway.databinding.FragmentProfileBinding
import com.skipissue.maxway.presentation.adapter.FoodsAdapter
import com.skipissue.maxway.presentation.screens.productDetail.ProductsDetailsViewModel

class ProfileFragment:Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Settings.setOnClickListener {
           // parentFragmentManager.beginTransaction().replace(R.id.container,Settings()).addToBackStack("toSettings").commit()
        }

        binding.malumotlar.setOnClickListener {
           // parentFragmentManager.beginTransaction().replace(R.id.container,Settings()).addToBackStack("toSettings").commit()
        }


    }

}
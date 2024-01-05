package com.skipissue.maxway.presentation.screens.lenguagefragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.FragmentLeanguageBinding
import com.skipissue.maxway.presentation.screens.mainfragment.MainFragment
import com.skipissue.maxway.presentation.screens.mainfragment.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.http2.Settings

@AndroidEntryPoint
class LanguageFragment(

) : Fragment() {
    private val viewModel: LanguageViewModel by viewModels()
    private lateinit var binding: FragmentLeanguageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeanguageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor = Color.WHITE

        binding.uzbek.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, MainFragment())
                .commit()
            viewModel.setLanguage(1)

        }

        binding.russuin.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, MainFragment())
                .commit()
            viewModel.setLanguage(2)

        }
        binding.english.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, MainFragment())
                .commit()
            viewModel.setLanguage(3)


        }


    }
}
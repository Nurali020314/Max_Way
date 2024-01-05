package com.skipissue.maxway.presentation.screens.basket

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.skipissue.maxway.R
import com.skipissue.maxway.data.local.entity.BasketEntity
import com.skipissue.maxway.databinding.FragmentBasketBinding
import com.skipissue.maxway.domain.entity.Basket
import com.skipissue.maxway.presentation.adapter.BasketAdapter
import com.skipissue.maxway.presentation.adapter.CategoriesAdapter
import com.skipissue.maxway.presentation.screens.mainfragment.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding
    private val viewModel: BasketViewModel by viewModels()
    private val adapter by lazy { BasketAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProducts()
       var a=0;
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.stateSuccess.collect { data ->
                data.forEach { item->
                    a+=item.count!!+item.cost!!.toInt()
                }

                adapter.submitList(data)
                Log.d("o1o", "$a ")
                binding.basketAllCost.text=a.toString()
            }
        }

        binding.recycler.adapter = adapter

        binding.delateAll.setOnClickListener {
            viewModel.deleteAll()
           adapter.submitList(emptyList())

        }
        adapter.setOnClickClickListener { id->
            Toast.makeText(requireContext(), "${adapter.currentList.get(id).id}", Toast.LENGTH_SHORT).show()
            viewModel.deleteById( adapter.currentList.get(id).id)
            val list = adapter.currentList.toMutableList()
            list.removeAt(id)
            adapter.submitList(list)

        }
        var cost=0;
        adapter.currentList.forEach { it
          cost+= it.cost?.toInt()!!
        }
        binding.basketAllCost.text=cost.toString()




    }

    override fun onResume() {
        super.onResume()
        viewModel.getProducts()
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.stateSuccess.collect { data ->
                adapter.submitList(data)
            }
        }

    }

}
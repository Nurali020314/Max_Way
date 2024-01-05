package com.skipissue.maxway.presentation.screens.productDetail

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skipissue.maxway.R
import com.skipissue.maxway.presentation.adapter.Suggest
import com.skipissue.maxway.databinding.DetailFragmentBinding
import com.skipissue.maxway.presentation.adapter.FoodsAdapter
import com.skipissue.maxway.presentation.adapter.SuggestAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment() : Fragment() {
    private lateinit var binding: DetailFragmentBinding
    private val viewModel: ProductsDetailsViewModel by viewModels()
    private val foodsAdapter by lazy { FoodsAdapter() }
    private val adapter by lazy { SuggestAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // requireActivity().window.statusBarColor = Color.parseColor("#ebe2d1")

        val receivedPath = arguments?.getString("pp")
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)

        binding.appBar.setExpanded(true, true)


        adapter.submitList(Suggest.getSuggest().toMutableList())

        requireActivity().findViewById<BottomNavigationView>(R.id.bottom).visibility = View.GONE

        viewModel.getProductsWithDetails("$receivedPath")

        binding.recycler.adapter = adapter

        adapter.setOnClickClickListener { id ->
           showCustomToast()
            id.cost?.let {
                id.image?.let { it1 ->
                    id.count?.let { it2 ->
                        viewModel.saveBasket(
                            name = id.nameUz!!,
                            description = id.descriptionUZ!!,
                            cost = it,
                            image = it1,
                            count = it2,
                            productId ="qqqq"
                        )
                    }
                }
            }
        }

        binding.exitDetail.setOnClickListener {
            val fragment = parentFragmentManager.findFragmentByTag("Detail")
            fragment?.let {
                parentFragmentManager.beginTransaction().remove(it).commit()
            }


        }

        lifecycleScope.launch {
            viewModel.stateSuccess.collect { data ->

                binding.save.setOnClickListener {
                    viewModel.getByProductId(data.id)
                    viewModel.count.observe(viewLifecycleOwner, Observer { it->
                        Log.d("dd", "$it ")

                    })
                    showCustomToast()

                    viewModel.saveBasket(
                        name = data.title.uz,
                        description = data.description.uz,
                        cost = data.out_price.toString(),
                        image = data.image,
                        count = binding.count.text.toString().toInt(),
                        productId = data.id
                    )





                }
                binding.detailName.text = data.title.uz
                binding.detailDescription.text = data.description.uz


                binding.collacpeToolbarLayout.apply {
                    title = "${data.title.uz}"
                    expandedTitleMarginBottom = 2000
                    setCollapsedTitleTextColor(Color.BLACK)
                    setExpandedTitleColor(Color.BLACK)
                }


                var path = "https://cdn.delever.uz/delever/${data.image}"
                Picasso.get()
                    .load(path)
                    .into(binding.detailImg)
                calculatePrice(data.out_price.toString())


            }
        }


    }


    override fun onStop() {
        super.onStop()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom).visibility = View.VISIBLE

    }


    fun calculatePrice(sum: String) = with(binding) {
        var a = 1
        detailPrice.text = sum.toString()
        minus.setOnClickListener {
            if (a > 1) {
                a--
                count.text = a.toString()
                detailPrice.text = (sum.toInt() * a).toString()
            }

        }
        plus.setOnClickListener {
            if (a < 50) {
                a++
                count.text = a.toString()
                detailPrice.text = (sum.toInt() * a).toString()
            }

        }

    }

    private fun showCustomToast() {
        val inflater = requireActivity().layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast_layout, requireView().findViewById(R.id.custom_toast_container))
        val toast = Toast(requireContext())
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
        toast.view = layout
        toast.show()
    }
}
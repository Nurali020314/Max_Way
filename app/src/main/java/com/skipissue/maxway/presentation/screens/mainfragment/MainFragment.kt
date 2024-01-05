package com.skipissue.maxway.presentation.screens.mainfragment

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.MainFragmentBinding
import com.skipissue.maxway.domain.entity.TabEntity
import com.skipissue.maxway.presentation.adapter.CategoriesAdapter
import com.skipissue.maxway.presentation.adapter.FoodsAdapter
import com.skipissue.maxway.presentation.adapter.TabsAdapter
import com.skipissue.maxway.presentation.screens.productDetail.DetailFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {
    private val binding: MainFragmentBinding by viewBinding()
    private val adapter by lazy { CategoriesAdapter() }
    private val foodsAdapter by lazy { FoodsAdapter() }
    private val rootView by lazy { requireView() }
    private val tabs by lazy { ArrayList<TabEntity>() }
    private val tabAdapter by lazy { TabsAdapter(tabs) }
    private var isUserScrolling = false
    private val viewModel: ProductsViewModel by viewModels()
    private val layoutManagerR by lazy { LinearLayoutManager(requireContext()) }
    private val smoothScroller by lazy {
        object : LinearSmoothScroller(requireContext()) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      //  requireActivity().window.statusBarColor = Color.WHITE
        lifecycleScope.launch {
            viewModel.stateSuccess.collect { data ->

                adapter.submitList(data.categories)
                tabs.clear()
                tabs.addAll(
                    adapter.currentList.map { it -> TabEntity(it.id, it.title.uz) }
                )
                tabAdapter.notifyDataSetChanged()
            }
        }

        binding.apply {
            tab.adapter = tabAdapter
            recycler.adapter = adapter
            recycler.layoutManager = layoutManagerR
            search.addTextChangedListener { text ->
                if (text?.toString().isNullOrEmpty())
                    clear.visibility = View.INVISIBLE
                else if (clear.visibility == View.INVISIBLE)
                    clear.visibility = View.VISIBLE
                else {
                }
            }
            clear.setOnClickListener {
                search.setText("")
            }


            adapter.setOnClickClickListener { index ->
                val detailFragment = DetailFragment()
                val fragmentTag = "Detail"
                val bundle = Bundle()
                bundle.putString("pp", index)
                detailFragment.arguments = bundle
                parentFragmentManager.beginTransaction().add(R.id.container, detailFragment,fragmentTag)
                    .addToBackStack("toDetail").commit()

            }

            foodsAdapter.setOnClickClickListener { index ->
                Log.d("Alib", "$index")
            }

            Picasso.get()
                .load("https://maxway.uz/_next/image?url=https%3A%2F%2Fcdn.delever.uz%2Fdelever%2Feedc6ad0-1c3c-40fb-a3f2-15b807b7ed30&w=1200&q=75")
                .into(binding.discountImg)

            viewModel.loading.observe(viewLifecycleOwner, Observer {
                it
                binding.progresbar.isVisible = it
            })
            val layoutManager = recycler.layoutManager as LinearLayoutManager
            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    when (newState) {
                        RecyclerView.SCROLL_STATE_IDLE -> {
                            if (isUserScrolling) {
                                val firstVisibleItemPosition =
                                    layoutManager.findFirstVisibleItemPosition()

                                if (firstVisibleItemPosition != 0) {
                                    binding.disc.animate().translationX(-1500F).setDuration(200L)
                                        .withEndAction {
                                            binding.disc.visibility = View.GONE
                                        }

                                } else {
                                    binding.disc.animate().translationX(5F).duration = 200
                                    binding.disc.visibility = View.VISIBLE


                                }

                                if (firstVisibleItemPosition != RecyclerView.NO_POSITION) {
                                    tabAdapter.select(firstVisibleItemPosition)
                                    //binding.tab.scrollToPosition(firstVisibleItemPosition)
                                    binding.tab.layoutManager?.smoothScrollToPosition(
                                        binding.tab,
                                        null,
                                        firstVisibleItemPosition
                                    )
                                    tabAdapter.notifyDataSetChanged()
                                }
                            }
                            isUserScrolling = false
                        }

                        RecyclerView.SCROLL_STATE_DRAGGING, RecyclerView.SCROLL_STATE_SETTLING -> {
                            isUserScrolling = true
                        }
                    }
                }


            })
        }


        // binding.tab.smoothScrollToPosition(5)

        // binding.tab.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        //     override fun onGlobalLayout() {
        //         binding.tab.viewTreeObserver.removeOnGlobalLayoutListener(this)
        //         binding.tab.scrollToPosition(4)
        //     }
        // })


        tabAdapter.setOnClickClickListener { index ->
            Toast.makeText(requireContext(), "$index", Toast.LENGTH_SHORT).show()
            if (index == tabAdapter.list.size - 1) {
                binding.recycler.smoothScrollToPosition(index)
                isUserScrolling = false
            } else {
                smoothScroller.targetPosition = index
                layoutManagerR.startSmoothScroll(smoothScroller)
            }
        }
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom).visibility = View.VISIBLE
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            val isKeyboardVisible = keypadHeight > screenHeight * 0.10

            if (isKeyboardVisible) {
                // Keyboard is open
                requireActivity().findViewById<BottomNavigationView>(R.id.bottom).visibility =
                    View.GONE
            } else {
                // Keyboard is closed
                // requireActivity().findViewById<BottomNavigationView>(R.id.bottom).visibility =
                //     View.VISIBLE
            }
        }
    }

}
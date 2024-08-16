package com.example.e_shop.main.presentation.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentHomeBinding
import com.example.e_shop.main.domain.model.Category
import com.example.e_shop.main.domain.model.ParentItem
import com.example.e_shop.main.domain.model.Product
import com.example.e_shop.main.presentation.category.vm.CategoryViewModel
import com.example.e_shop.main.presentation.home.adapter.ChildAdapter
import com.example.e_shop.main.presentation.home.adapter.ParentAdapter
import com.example.e_shop.main.presentation.home.vm.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // TODO Build MultiView recycler view. Nested recycler view sucks
    private val homeViewModel by viewModels<HomeViewModel>()
    private val categoryViewModel: CategoryViewModel by viewModels()
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val parentAdapter by lazy { ParentAdapter(clickEvent = ::parentClickEvents) }
    private val childAdapter by lazy { ChildAdapter(clickEvents = ::childClickEvents) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView()
            favoriteIcon.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment) }
        }
    }

    private fun recyclerView() {
        homeViewModel.getAllProducts()
        homeViewModel.getAllProductsResult.observe(viewLifecycleOwner) { response ->

            if (response.isSuccessful) {
                Log.d("code", response.code().toString())

                response.body()?.let { product ->
                    val productList: MutableList<Product> = product.toMutableList()
                    childAdapter.differ.submitList(productList)

                    val parentItems = listOf(
                        ParentItem(1, "Parent 1", "",productList),
                        ParentItem(2, "Parent 2", "",productList)
                    )

                    Log.d("code", parentItems.toString())
                    binding.parentRcView.adapter = parentAdapter
                    parentAdapter.differ.submitList(parentItems)
                    binding.parentRcView.layoutManager = LinearLayoutManager(requireContext())
                }
            } else {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun parentClickEvents(clickEvents: ParentAdapter.ParentClickEvent, product: ParentItem) {
        val bottomBar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav_view)
        when(clickEvents) {
            ParentAdapter.ParentClickEvent.ITEM -> { Toast.makeText(requireContext(), "Developing...", Toast.LENGTH_SHORT).show() }
            ParentAdapter.ParentClickEvent.SEE_ALL -> { findNavController().navigate(R.id.action_homeFragment_to_categoryFragment) }
            ParentAdapter.ParentClickEvent.ADD_TO_FAVORITE -> { Snackbar.make(bottomBar, "Developing...", Snackbar.LENGTH_SHORT).show() }
        }
    }

    private fun childClickEvents(clickEvents: ChildAdapter.ChildClickEvents, product: Product) {
        when(clickEvents) {
            ChildAdapter.ChildClickEvents.ITEM -> { findNavController().navigate(R.id.action_homeFragment_to_categoryItemFragment) }
            ChildAdapter.ChildClickEvents.ADD_TO_FAVORITE -> {}
        }
    }
}
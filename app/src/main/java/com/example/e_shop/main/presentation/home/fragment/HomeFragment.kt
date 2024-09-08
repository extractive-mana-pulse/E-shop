package com.example.e_shop.main.presentation.home.fragment

import android.os.Bundle
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
import com.example.e_shop.main.domain.model.ParentItem
import com.example.e_shop.main.domain.model.Product
import com.example.e_shop.main.presentation.category.vm.CategoryViewModel
import com.example.e_shop.main.presentation.favorite.vm.DatabaseViewModel
import com.example.e_shop.main.presentation.home.adapter.ChildAdapter
import com.example.e_shop.main.presentation.home.adapter.ParentAdapter
import com.example.e_shop.main.presentation.home.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO Build MultiView recycler view. Nested recycler view sucks
// TODO parent layout color is test colorSurface and this is only test to observe how does it looks like.

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private val dbViewModel by viewModels<DatabaseViewModel>()
    private val categoryViewModel by viewModels<CategoryViewModel>()
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val childAdapter by lazy { ChildAdapter(clickEvents = ::childClickEvents) }
    private val parentAdapter by lazy { ParentAdapter(clickEvent = ::parentClickEvents, dbViewModel = dbViewModel, lifecycleOwner = viewLifecycleOwner) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView()
            favoriteIcon.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment) }
        }
    }

    private fun recyclerView() {

        homeViewModel.getPaginationProducts()
        homeViewModel.getProductsWithPagination.observe(viewLifecycleOwner) { response ->

            if (response.isSuccessful) {
                response.body()?.let { products ->
                    binding.parentRcView.adapter = parentAdapter
                    binding.parentRcView.layoutManager = LinearLayoutManager(requireContext())
                    childAdapter.differ.submitList(products.toList())
                    val parentItems = createParentItems(products)
                    parentAdapter.differ.submitList(parentItems)
                }
            } else {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createParentItems(products: List<Product>): List<ParentItem> {
        val categoryName = products.map { category -> category.category?.name }
        return listOf(
            ParentItem(1, categoryName.get(1).toString(), "", products),
            ParentItem(2, categoryName.last().toString(), "", products)
        )
    }

    private fun parentClickEvents(clickEvents: ParentAdapter.ParentClickEvent, product: ParentItem) {
        when(clickEvents) {
            ParentAdapter.ParentClickEvent.ITEM -> {}
            ParentAdapter.ParentClickEvent.ADD_TO_FAVORITE -> {}
            ParentAdapter.ParentClickEvent.SEE_ALL -> { findNavController().navigate(R.id.action_homeFragment_to_categoryFragment) }
        }
    }

    private fun childClickEvents(clickEvents: ChildAdapter.ChildClickEvents, product: Product) {
        when(clickEvents) {
            ChildAdapter.ChildClickEvents.ITEM -> {}
            ChildAdapter.ChildClickEvents.ADD_TO_FAVORITE -> {}
        }
    }
}

//            if (response.isSuccessful) {
//
//                response.body()?.let { product ->
//                    val productList: MutableList<Product> = product.toMutableList()
//                    childAdapter.differ.submitList(productList)
//
//                    val parentItems = listOf(
//                        ParentItem(1, "title of the parent", "", productList),
//                        ParentItem(2, product.first().category?.name, "", productList)
//                    )
//                    binding.parentRcView.adapter = parentAdapter
//                    parentAdapter.differ.submitList(parentItems)
//                    binding.parentRcView.layoutManager = LinearLayoutManager(requireContext())
//                }
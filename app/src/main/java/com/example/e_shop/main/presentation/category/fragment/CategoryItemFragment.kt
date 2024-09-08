package com.example.e_shop.main.presentation.category.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentCategoryItemBinding
import com.example.e_shop.main.domain.model.Product
import com.example.e_shop.main.presentation.category.adapter.CategoryItemAdapter
import com.example.e_shop.main.presentation.category.vm.CategoryViewModel
import com.example.e_shop.main.presentation.favorite.vm.DatabaseViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryItemFragment : Fragment() {

    private val dbViewModel by viewModels<DatabaseViewModel>()
    private val categoryViewModel by viewModels<CategoryViewModel>()
    private val binding by lazy { FragmentCategoryItemBinding.inflate(layoutInflater) }
    private val categoryItemAdapter by lazy { CategoryItemAdapter(clickEvent = ::categoryItemClickEvents) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            val title = arguments?.getString("name")
            val id = arguments?.getString("id")

            categoryNameTv.text = title

            categoryViewModel.getAllProductsByCategory(id!!.toInt())
            categoryViewModel.allProductsByCategory.observe(viewLifecycleOwner) { response ->
                if (response.isSuccessful) {
                    response.body()?.let { categories ->

                        val categoryList: MutableList<Product> = categories.toMutableList()
                        categoryItemAdapter.differ.submitList(categoryList)

                        val productIds = categoryList.mapNotNull { it.id }

                        viewLifecycleOwner.lifecycleScope.launch {
                            dbViewModel.checkIfItemsExist(productIds).observe(viewLifecycleOwner) { existenceStates ->
                                existenceStates.forEachIndexed { index, exists ->
                                    val product = categoryList[index]
                                    when (exists) {
                                        true -> {
                                            Log.d("item", "Product with ID ${product.id} is in the database")
                                        }
                                        false -> {
                                            Log.d("item", "Product with ID ${product.id} is not in the database")
                                        }
                                    }
                                }
                            }
                        }
                    }

                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }

            categoryItemRcView.adapter = categoryItemAdapter
            categoryItemRcView.layoutManager = GridLayoutManager(requireContext(), 2)

            categoryItemPageBackBtn.setOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun categoryItemClickEvents(categoryItemClickHandler: CategoryItemAdapter.CategoryItemClickHandler, product: Product) {
        when(categoryItemClickHandler) {
            CategoryItemAdapter.CategoryItemClickHandler.ITEM -> {
                val bundle = Bundle().apply {
                    putParcelable("product", product)
                }
                findNavController().navigate(R.id.detailFragment, bundle)
            }

            CategoryItemAdapter.CategoryItemClickHandler.ADD_TO_FAVORITE -> {
                dbViewModel.saveProduct(product)
                Snackbar.make(binding.root, "Added", Snackbar.ANIMATION_MODE_SLIDE).show()
            }
        }
    }
}
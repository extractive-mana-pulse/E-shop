package com.example.e_shop.main.presentation.category.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_shop.databinding.FragmentCategoryItemBinding
import com.example.e_shop.main.domain.model.Product
import com.example.e_shop.main.presentation.category.adapter.CategoryItemAdapter
import com.example.e_shop.main.presentation.category.vm.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryItemFragment : Fragment() {

    private val categoryItemAdapter by lazy { CategoryItemAdapter() }
    private val categoryViewModel : CategoryViewModel by viewModels()
    private val binding by lazy { FragmentCategoryItemBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            categoryItemPageBackBtn.setOnClickListener { findNavController().navigateUp() }

            val categoryId = arguments?.getString("id")
            val categoryName = arguments?.getString("name")

            categoryNameTv.text = categoryName

            categoryItemRcView.adapter = categoryItemAdapter
            categoryItemRcView.layoutManager = GridLayoutManager(requireContext(), 2)

            categoryViewModel.getAllProductsByCategory(categoryId!!.toInt())

            categoryViewModel.allProductsByCategory.observe(viewLifecycleOwner) { response ->
                if (response.isSuccessful) {
                    response.body()?.let { categories ->

                        val categoryList: MutableList<Product> = categories.toMutableList()
                        categoryItemAdapter.differ.submitList(categoryList)
                    }

                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
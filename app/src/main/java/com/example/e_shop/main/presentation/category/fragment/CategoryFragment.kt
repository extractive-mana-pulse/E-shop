package com.example.e_shop.main.presentation.category.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_shop.databinding.FragmentCategoryBinding
import com.example.e_shop.main.domain.model.Category
import com.example.e_shop.main.presentation.category.adapter.CategoryAdapter
import com.example.e_shop.main.presentation.category.vm.CategoryViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private val categoryAdapter by lazy { CategoryAdapter() }
    private val categoryViewModel : CategoryViewModel by viewModels()
    private val binding by lazy { FragmentCategoryBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            categoryPageBackBtn.setOnClickListener { findNavController().navigateUp() }

            categoriesRcView.adapter = categoryAdapter
            categoriesRcView.layoutManager = LinearLayoutManager(requireContext())

            categoryViewModel.getAllCategories()

            categoryViewModel.category.observe(viewLifecycleOwner) { response ->

                if (response.isSuccessful) {
                    response.body()?.let { categories ->

                        val categoryList: MutableList<Category> = categories.toMutableList()
                        categoryAdapter.differ.submitList(categoryList)

                    }

                } else {
                    Snackbar.make(snackbarView, "Something went wrong, please try again,", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}
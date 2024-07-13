package com.example.e_shop.main.presentation.favorite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_shop.databinding.FragmentFavoriteBinding
import com.example.e_shop.main.domain.model.Product
import com.example.e_shop.main.presentation.category.adapter.CategoryAdapter
import com.example.e_shop.main.presentation.favorite.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteAdapter by lazy { FavoriteAdapter() }
    private val binding by lazy { FragmentFavoriteBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            favoriteRcView.adapter = favoriteAdapter
            favoriteRcView.layoutManager = GridLayoutManager(requireContext(), 2)

//            favoriteRcView.getAllProductsByCategory(categoryId!!.toInt())

//            categoryViewModel.allProductsByCategory.observe(viewLifecycleOwner) { response ->
//                if (response.isSuccessful) {
//                    response.body()?.let { categories ->
//
//                        val categoryList: MutableList<Product> = categories.toMutableList()
//                        categoryItemAdapter.differ.submitList(categoryList)
//                    }
//
//                } else {
//                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
//                }
//            }

        }
    }
}
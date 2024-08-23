package com.example.e_shop.main.presentation.favorite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentFavoriteBinding
import com.example.e_shop.main.domain.model.Product
import com.example.e_shop.main.presentation.favorite.adapter.FavoriteAdapter
import com.example.e_shop.main.presentation.favorite.vm.DatabaseViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val dbViewModel by viewModels<DatabaseViewModel>()
    private val favoriteAdapter by lazy { FavoriteAdapter(clickEvent = ::favoriteClickEvents) }

    private val binding by lazy { FragmentFavoriteBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            dbViewModel.getSavedProducts().observe(viewLifecycleOwner) { articles ->
                favoriteAdapter.differ.submitList(articles)
                if (articles.isEmpty()) {
                    emptyFavoriteLayout.root.visibility = View.VISIBLE
                } else {
                    emptyFavoriteLayout.root.visibility = View.GONE
                }
            }

            favoritePageBackBtn.setOnClickListener { findNavController().navigateUp() }

            emptyFavoriteLayout.emptyWishlistBtn.setOnClickListener { findNavController().navigate(R.id.action_favoriteFragment_to_categoryFragment) }

            favoriteRcView.adapter = favoriteAdapter
            favoriteRcView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun favoriteClickEvents(favoriteAdapterClickHandler: FavoriteAdapter.FavoriteAdapterClickHandler, product: Product) {
        when(favoriteAdapterClickHandler) {

            FavoriteAdapter.FavoriteAdapterClickHandler.ITEM -> {
                val bundle = Bundle().apply {
                    putParcelable("product", product)
                }
                findNavController().navigate(R.id.detailFragment, bundle)
            }

            FavoriteAdapter.FavoriteAdapterClickHandler.REMOVE_FROM_WISHLIST -> {
                dbViewModel.deleteArticle(product)
                Snackbar.make(binding.root, "Removed", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){ dbViewModel.saveProduct(product) }.show()
                }
            }
        }
    }
}
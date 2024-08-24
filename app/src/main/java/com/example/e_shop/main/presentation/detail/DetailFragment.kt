package com.example.e_shop.main.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentDetailBinding
import com.example.e_shop.main.domain.model.Product
import com.example.e_shop.main.presentation.favorite.vm.DatabaseViewModel
import com.example.e_shop.main.presentation.favorite.vm.ItemExistenceState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private val dbViewModel by viewModels<DatabaseViewModel>()
    private var isItemInWishlist: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = arguments?.getParcelable<Product>("product")

        binding.apply {
            product?.id?.let { dbViewModel.checkIfItemExists(it) }
            dbViewModel.itemExistenceState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is ItemExistenceState.Exists -> {
                        isItemInWishlist = true
                        detailToolbar.menu.findItem(R.id.add_to_wishlist).icon = ContextCompat.getDrawable(requireContext(), R.drawable.heart_filled)
                    }
                    is ItemExistenceState.NotExists -> {
                        isItemInWishlist = false
                        detailToolbar.menu.findItem(R.id.add_to_wishlist).icon = ContextCompat.getDrawable(requireContext(), R.drawable.heart_detail)
                    }
                }
            }

            detailToolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.add_to_wishlist -> {
                        if (isItemInWishlist) {
                            product?.let { dbViewModel.deleteArticle(it) }
                            Snackbar.make(root, "Removed", Snackbar.ANIMATION_MODE_SLIDE).show()
                            updateWishlistIcon(R.drawable.heart_detail)
                            isItemInWishlist = false
                        } else {
                            product?.let { dbViewModel.saveProduct(it) }
                            Snackbar.make(root, "Added", Snackbar.ANIMATION_MODE_SLIDE).show()
                            updateWishlistIcon(R.drawable.heart_filled)
                            isItemInWishlist = true
                        }
                        true
                    }
                    else -> false
                }
            }


            imageSlider.setSlideAnimation(AnimationTypes.BACKGROUND_TO_FOREGROUND)
            detailToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            addToBasketBtn.setOnClickListener { Toast.makeText(requireContext(), "Developing...", Toast.LENGTH_SHORT).show() }

            product?.let { product ->
                loadImages(product.images)
                productName.text = product.title
                productPrice.text = "${product.price}$"
                productDescription.text = product.description
            }

            var counter: Int = quantityTv.text.toString().toInt()
            val pricePerItem = productPrice.text.toString().replace("$", "").toInt()

            fun updateTotalPrice() {
                val total = pricePerItem * counter
                totalItemPrice.text = "$total$"
            }

            updateTotalPrice()

            increaseQuantity.setOnClickListener {
                counter++
                quantityTv.text = counter.toString()
                updateTotalPrice()
            }

            decreaseQuantity.setOnClickListener {
                if (counter > 1) {
                    counter--
                    quantityTv.text = counter.toString()
                    updateTotalPrice()
                }
            }
        }
    }

    private fun updateWishlistIcon(iconResId: Int) {
        binding.detailToolbar.menu.findItem(R.id.add_to_wishlist).icon = ContextCompat.getDrawable(requireContext(), iconResId)
    }

    private fun loadImages(images: ArrayList<String>) {
        val imageList = ArrayList<SlideModel>()

        for (image in images) {
            imageList.add(SlideModel(image))
        }
        binding.imageSlider.setImageList(imageList)
    }
}
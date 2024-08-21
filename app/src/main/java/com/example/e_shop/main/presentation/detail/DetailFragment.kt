package com.example.e_shop.main.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.models.SlideModel
import com.example.e_shop.databinding.FragmentDetailBinding
import com.example.e_shop.main.domain.model.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productItem = arguments?.getParcelable<Product>("product")
        binding.apply {

            detailToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

            productItem?.let { product ->
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

    private fun loadImages(images: ArrayList<String>) {
        val imageList = ArrayList<SlideModel>()

        for (image in images) {
            imageList.add(SlideModel(image))
        }
        binding.imageSlider.setImageList(imageList)
    }
}
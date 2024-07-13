package com.example.e_shop.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentBasketBinding


class BasketFragment : Fragment() {

    private val binding by lazy { FragmentBasketBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            basketExploreCategoriesBtn.setOnClickListener { findNavController().navigate(R.id.action_basketFragment_to_categoryFragment) }

        }
    }
}
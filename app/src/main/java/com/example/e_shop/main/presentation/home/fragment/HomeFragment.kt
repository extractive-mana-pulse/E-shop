package com.example.e_shop.main.presentation.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            categoryBtn.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_categoryFragment) }

            favoriteIcon.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment) }
        }
    }
}
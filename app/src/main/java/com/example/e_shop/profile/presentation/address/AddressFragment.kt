package com.example.e_shop.profile.presentation.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_shop.databinding.FragmentAddressBinding
import com.google.android.material.snackbar.Snackbar

class AddressFragment : Fragment() {

    private val binding by lazy { FragmentAddressBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            addressToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            addAddressBtn.setOnClickListener {
                Snackbar.make(root,"Developing...",Snackbar.ANIMATION_MODE_SLIDE).show()
                // TODO: write logic that illustrate alert and btn state will stay disabled when text fields are empty. Use TextWatchers
            }
        }
    }
}
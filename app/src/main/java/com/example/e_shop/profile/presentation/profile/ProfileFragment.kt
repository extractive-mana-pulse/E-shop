package com.example.e_shop.profile.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentProfileBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            signOutTv.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_loginFragment) }
            profileAddressLayout.root.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_addressFragment) }
            profilePaymentLayout.root.setOnClickListener { Snackbar.make(root, "Developing...", Snackbar.ANIMATION_MODE_SLIDE).show() }
            profileSettingsLayout.root.setOnClickListener { Snackbar.make(root, "Developing...", Snackbar.ANIMATION_MODE_SLIDE).show() }
            profileWishlistLayout.root.setOnClickListener { Snackbar.make(root, "Developing...", Snackbar.ANIMATION_MODE_SLIDE).show() }
            profileCredLayout.profileEditBtn.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment) }
        }
    }
}
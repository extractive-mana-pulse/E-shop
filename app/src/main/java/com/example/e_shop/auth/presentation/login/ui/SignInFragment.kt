package com.example.e_shop.auth.presentation.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            continueBtn.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_homeFragment) }

            createAccountTv.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment) }

            regWithAppleLayout.setOnClickListener { Snackbar.make(loginRootLayout, "Developing...", Snackbar.LENGTH_SHORT).show() }

            regWithGoogleLayout.setOnClickListener { Snackbar.make(loginRootLayout, "Developing...", Snackbar.LENGTH_SHORT).show() }

            regWithFacebookLayout.setOnClickListener { Snackbar.make(loginRootLayout, "Developing...", Snackbar.LENGTH_SHORT).show() }
        }
    }
}
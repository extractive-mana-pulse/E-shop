package com.example.e_shop.profile.presentation.address.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentAddressBinding
import com.example.e_shop.profile.domain.model.Address
import com.example.e_shop.profile.presentation.address.vm.AddressViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddressFragment : Fragment() {

    private val addressViewModel: AddressViewModel by viewModels()
    private val binding by lazy { FragmentAddressBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            backBtn.setOnClickListener { findNavController().navigateUp() }

            addAddressBtn.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    addressViewModel.saveAddress(
                        address = Address(
                            null,
                            cityEt.text.toString().trim(),
                            streetAddressEt.text.toString().trim(),
                            stateEt.text.toString().trim(),
                            zipCodeEt.text.toString().trim()
                        )
                    )
                    findNavController().navigate(R.id.action_addressFragment_to_listOfAddressesFragment)
                    Snackbar.make(root,"Address added successfully", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}
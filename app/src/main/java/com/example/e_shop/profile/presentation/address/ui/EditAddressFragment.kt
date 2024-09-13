package com.example.e_shop.profile.presentation.address.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentEditAddressBinding
import com.example.e_shop.profile.domain.model.Address
import com.example.e_shop.profile.presentation.address.vm.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditAddressFragment : Fragment() {

    private val addressViewModel by viewModels<AddressViewModel>()
    private val binding by lazy { FragmentEditAddressBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

//            editAddressToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

            backBtn.setOnClickListener { findNavController().navigateUp() }
            val bundle = arguments?.getParcelable("address") as? Address

            if (bundle != null) {
                cityEt.setText(bundle.city)
                streetAddressEt.setText(bundle.streetAddress)
                stateEt.setText(bundle.state)
                zipCodeEt.setText(bundle.zipCode)
            }

            deleteAddress.setOnClickListener {
                bundle?.let { addressViewModel.deleteAddress(it) }
                findNavController().navigate(R.id.action_editAddressFragment_to_listOfAddressesFragment)
            }

//            editAddressToolbar.setOnMenuItemClickListener { item ->
//                when(item.itemId) {
//                    R.id.delete_address -> {
//                        bundle?.let { addressViewModel.deleteAddress(it) }
//                        findNavController().navigate(R.id.action_editAddressFragment_to_listOfAddressesFragment)
//                    }
//                }
//                true
//            }

            editAddressBtn.setOnClickListener {
                addressViewModel.updateAddress(
                    address = Address(
                        bundle?.id, cityEt.text.toString().trim(),
                        streetAddressEt.text.toString().trim(),
                        stateEt.text.toString().trim(),
                        zipCodeEt.text.toString().trim()
                    )
                )
                findNavController().navigate(R.id.action_editAddressFragment_to_listOfAddressesFragment)
            }
        }
    }
}
package com.example.e_shop.profile.presentation.address.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentListOfAddressesBinding
import com.example.e_shop.profile.domain.model.Address
import com.example.e_shop.profile.presentation.address.adapter.AddressAdapter
import com.example.e_shop.profile.presentation.address.vm.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfAddressesFragment : Fragment() {

    private val addressViewModel: AddressViewModel by viewModels()
    private val adapter by lazy { AddressAdapter(clickEvent=::addressClickEvents ) }
    private val binding by lazy { FragmentListOfAddressesBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            createAddressFab.setOnClickListener { findNavController().navigate(R.id.action_listOfAddressesFragment_to_addressFragment) }

            addressViewModel.getAllAddresses().observe(viewLifecycleOwner) { address ->
                adapter.differ.submitList(address)
                if (address.isEmpty()) {
                    createAddress.visibility = View.VISIBLE
                    createAddressFab.visibility = View.GONE
                } else {
                    createAddress.visibility = View.GONE
                    createAddressFab.visibility = View.VISIBLE
                }
            }

            listOfAddresses.adapter = adapter
            listOfAddresses.layoutManager = LinearLayoutManager(requireContext())

            backBtn.setOnClickListener { findNavController().navigateUp() }

            createAddress.setOnClickListener { findNavController().navigate(R.id.action_listOfAddressesFragment_to_addressFragment) }
        }
    }

    private fun addressClickEvents(addressAdapterClickHandler: AddressAdapter.AddressAdapterClickHandler, address: Address) {
        when(addressAdapterClickHandler) {
            AddressAdapter.AddressAdapterClickHandler.ITEM -> {
                Toast.makeText(requireContext(), "Developing...", Toast.LENGTH_SHORT).show()
//                val bundle = Bundle().apply {
//                    putParcelable("address", address)
//                }
//                findNavController().navigate(R.id.action_listOfAddressesFragment_to_addressFragment, bundle)
            }

            AddressAdapter.AddressAdapterClickHandler.EDIT -> {
                val bundle = Bundle().apply {
                    putParcelable("address", address)
                }
                findNavController().navigate(R.id.action_listOfAddressesFragment_to_editAddressFragment, bundle)
            }
        }
    }
}
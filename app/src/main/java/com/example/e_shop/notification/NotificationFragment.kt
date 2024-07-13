package com.example.e_shop.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentNotificationBinding


class NotificationFragment : Fragment() {

    private val binding by lazy { FragmentNotificationBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            exploreCategoriesBtn.setOnClickListener { findNavController().navigate(R.id.action_notificationFragment_to_categoryFragment) }
        }
    }
}
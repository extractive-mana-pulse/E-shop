package com.example.e_shop.profile.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.e_shop.R
import com.example.e_shop.auth.presentation.login.ui.GoogleAuthUiClient
import com.example.e_shop.databinding.FragmentProfileBinding
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = requireActivity(),
            oneTapClient = Identity.getSignInClient(requireActivity())
        )
    }

    private val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            val userData = googleAuthUiClient.getSignedInUser()
            userData?.let {
                if (userData.profilePictureUrl != null) {
                    userAvatar.load(userData.profilePictureUrl) {
                        crossfade(true)
                        placeholder(R.drawable.person)
                    }
                }
                profileCredLayout.profileFullname.text = it.username ?: "Something went definitely wrong!"
                profileCredLayout.profileEmail.text = it.email ?: "Something went definitely wrong!"
            }

            signOutTv.setOnClickListener {
                lifecycleScope.launch {
                    googleAuthUiClient.signOut()
                    Toast.makeText(requireContext(), "Signed out", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
                }
            }

            profileAddressLayout.root.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_listOfAddressesFragment) }
            profilePaymentLayout.root.setOnClickListener { Snackbar.make(root, "Developing...", Snackbar.ANIMATION_MODE_SLIDE).show() }
            profileSettingsLayout.root.setOnClickListener { Snackbar.make(root, "Developing...", Snackbar.ANIMATION_MODE_SLIDE).show() }
            profileWishlistLayout.root.setOnClickListener { Snackbar.make(root, "Developing...", Snackbar.ANIMATION_MODE_SLIDE).show() }
            profileCredLayout.profileEditBtn.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment) }

        }
    }
}
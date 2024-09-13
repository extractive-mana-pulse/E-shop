package com.example.e_shop.auth.presentation.signup.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.e_shop.R
import com.example.e_shop.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val binding by lazy { FragmentSignUpBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            createAccountPageContinueBtn.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    auth.createUserWithEmailAndPassword(
                        createAccountPageEmailAddressEt.text.toString().trim(),
                        createAccountPagePasswordEt.text.toString().trim()
                    ).addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            val user = FirebaseAuth.getInstance().currentUser
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(createAccountPageFirstNameEt.text.toString())
                                .setPhotoUri(Uri.parse("android.resource://your.package.name/${R.drawable.profile}")) // Use correct URI
                                .build()

                            user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                                if (updateTask.isSuccessful) {
                                    Toast.makeText(requireContext(), "Updated successfully", Toast.LENGTH_SHORT).show()
                                    findNavController().navigate(R.id.action_createAccountFragment_to_homeFragment)
                                } else {
                                    Log.e("TAG", "Error updating user profile.", updateTask.exception)
                                }
                            }
                        } else {
                            Snackbar.make(root, "Something went wrong, please try again!", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            createAccountPageBackBtn.setOnClickListener { findNavController().navigateUp() }
        }
    }
}
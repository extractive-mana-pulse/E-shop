package com.example.e_shop.auth.presentation.signup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.e_shop.R
import com.example.e_shop.auth.presentation.signup.sealed.SignUp
import com.example.e_shop.auth.presentation.signup.vm.SignUpViewModel
import com.example.e_shop.databinding.FragmentCreateAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val authViewModel : SignUpViewModel by viewModels()
    private val binding by lazy { FragmentCreateAccountBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            val email = createAccountPageFirstNameEt.text.toString().trim()
            val name = createAccountPageFirstNameEt.text.toString().trim()
            val password = createAccountPageFirstNameEt.text.toString().trim()

            viewLifecycleOwner.lifecycleScope.launch {

                createAccountPageContinueBtn.setOnClickListener { authViewModel.signUp(name, email, password, "https://i.sstatic.net/l60Hf.png") }

                authViewModel.signUpResult.collect {
                    when(it){

                        is SignUp.Error -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }

                        SignUp.Nothing -> { /**/ }

                        is SignUp.Success -> {
                            findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment)
                        }
                    }
                }
            }

            createAccountPageBackBtn.setOnClickListener { findNavController().navigateUp() }
        }
    }
}
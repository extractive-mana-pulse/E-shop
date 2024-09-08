package com.example.e_shop.auth.presentation.login.ui

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.e_shop.R
import com.example.e_shop.auth.presentation.signup.vm.SignUpViewModel
import com.example.e_shop.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private val signUpViewModel by viewModels<SignUpViewModel>()
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = requireActivity(),
            oneTapClient = Identity.getSignInClient(requireActivity())
        )
    }
    private lateinit var launcher: ActivityResultLauncher<IntentSenderRequest>
    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            if (googleAuthUiClient.getSignedInUser() != null) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }

            launcher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    lifecycleScope.launch {
                        val signInResult = googleAuthUiClient.signInWithIntent(result.data ?: return@launch)
                        signUpViewModel.onSignInResult(signInResult)
                        if (signUpViewModel.state.value.isSignInSuccessful) {
                            Toast.makeText(requireContext(), "Sign in successful", Toast.LENGTH_LONG).show()
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        }
                    }
                }
            }

            regWithGoogleLayout.setOnClickListener {
                lifecycleScope.launch {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    signInIntentSender?.let {
                        launcher.launch(IntentSenderRequest.Builder(it).build())
                    }
                }
            }

            createAccountTv.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment) }
            regWithAppleLayout.setOnClickListener { Snackbar.make(loginRootLayout, "Developing...", Snackbar.LENGTH_SHORT).show() }
            regWithFacebookLayout.setOnClickListener { Snackbar.make(loginRootLayout, "Developing...", Snackbar.LENGTH_SHORT).show() }
        }
    }
}
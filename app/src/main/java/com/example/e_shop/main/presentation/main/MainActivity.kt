package com.example.e_shop.main.presentation.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.e_shop.R
import com.example.e_shop.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navController by lazy { (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        /** NOTE: in case when google splash screen conflict with Material3 or MaterialComponents
         * try to write code below before onCreate and setContentView
         * */
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {

            bottomNavView.setupWithNavController(navController)

            val fragmentsToHideBottomNav = setOf(
                R.id.loginFragment,
                R.id.createAccountFragment,
                R.id.categoryFragment,
                R.id.categoryItemFragment,
                R.id.favoriteFragment,
                R.id.editProfileFragment,
                R.id.detailFragment

                )

            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id in fragmentsToHideBottomNav) {
                    binding.bottomNavView.visibility = View.GONE
                } else {
                    binding.bottomNavView.visibility = View.VISIBLE
                }
            }

        }
    }
}
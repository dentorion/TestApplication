package com.entin.testapplication.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.entin.testapplication.R
import com.entin.testapplication.databinding.ActivityMainBinding
import com.entin.testapplication.screens.main.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainScreenViewModel by viewModels()

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        fetchData()
        showSplashScreen(viewModel)
        setupActionBar()

        setContentView(binding.root)
    }

    private fun setupActionBar() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    private fun showSplashScreen(mainScreenViewModel: MainScreenViewModel) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainScreenViewModel.stateSplashScreen.value
            }
        }
    }

    private fun fetchData() {
        viewModel.downloadUsers()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
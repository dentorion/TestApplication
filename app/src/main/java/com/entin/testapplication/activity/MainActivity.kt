package com.entin.testapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.entin.data.model.apiOne.ApiOneResponse
import com.entin.testapplication.databinding.ActivityMainBinding
import com.entin.testapplication.screens.main.MainScreenViewModel
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchData()
        showSplashScreen(viewModel)
        bindView()
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

    private fun bindView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package com.entin.testapplication.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.entin.domain.model.UserDomain
import com.entin.testapplication.R
import com.entin.testapplication.databinding.FragmentMainScreenBinding
import com.entin.testapplication.screens.main.epoxy.user
import com.entin.testapplication.utils.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainScreenViewModel>()
    private var snackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.stateSplashScreen.collect { splashScreenShowing ->
                    when (splashScreenShowing) {
                        true -> Unit
                        false -> {
                            if (!viewModel.isSuccessDownload) {
                                failResult()
                            } else {
                                getUsers()
                            }
                        }
                    }
                }
            }
        }

        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getUsers() {
        viewModel.getSavedUsers()
        observeViewState()
    }

    private fun observeViewState() {
        observe(viewModel.stateMainScreen) { viewState ->
            when (viewState) {
                is Fail -> failResult()
                is Pending -> pendingResult()
                is Success -> successResult(viewState)
            }
        }
    }

    private fun successResult(viewResult: Success<MainScreenViewState>) {
        binding.mainScreenFragmentProgressBar.isVisible = false
        showUsers(viewResult.data.usersList)
    }

    private fun pendingResult() {
        binding.mainScreenFragmentProgressBar.isVisible = true
    }

    private fun failResult() {
        binding.mainScreenFragmentProgressBar.isVisible = false
        snackbar?.dismiss()
        snackbar = binding.root.showSnackbar(R.string.error).also {
            it.show()
        }
    }

    private fun showUsers(usersList: List<UserDomain>) {
        binding.mainScreenEpoxyRecyclerview.withModels {
            usersList.forEach { user ->
                user {
                    id(user.name)
                    userDomainModel(user)
                    onClick {
                        findNavController().navigate(
                            MainScreenFragmentDirections.actionMainScreenFragmentToDetailScreenFragment(
                                user
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mainScreenEpoxyRecyclerview.apply {
            adapter = null
            layoutManager = null
            recycledViewPool.clear()
            clear()
        }
        _binding = null
    }
}
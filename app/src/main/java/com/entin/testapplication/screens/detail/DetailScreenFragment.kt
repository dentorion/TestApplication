package com.entin.testapplication.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.entin.domain.model.UserDomain
import com.entin.testapplication.databinding.FragmentDetailScreenBinding

class DetailScreenFragment : Fragment() {

    private var _binding: FragmentDetailScreenBinding? = null
    private val binding get() = _binding!!
    private val args: DetailScreenFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailScreenBinding.inflate(inflater, container, false)

        val user = args.user
        setAvatar(user.avatarUrl)
        setText(user)

        return binding.root
    }

    private fun setAvatar(avatarUrl: String) {
        Glide.with(binding.detailScreenAvatar)
            .load(avatarUrl)
            .transform(CenterCrop())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.detailScreenAvatar)
    }
    
    private fun setText(user: UserDomain) {
        binding.apply {
            detailScreenName.text = user.name
            detailScreenSource.text = user.source
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.antique_boss.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.antique_boss.settings.databinding.FragmentProfileEditBinding

class ProfileEditFragment : Fragment() {
    private var _binding: FragmentProfileEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_edit, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.profileEditToolbar.setNavigationIcon(R.drawable.ic_left_arrow)
        binding.profileEditToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}
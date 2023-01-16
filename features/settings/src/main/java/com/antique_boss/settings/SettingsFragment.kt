package com.antique_boss.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.antique_boss.settings.databinding.FragmentSettingsBinding
import com.bumptech.glide.Glide

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var settingsMenuItem: MenuItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        setupToolbar()
        setupViewState()
    }

    private fun setupToolbar() {
        binding.settingsToolbar.inflateMenu(R.menu.settings_toolbar_menu)
        settingsMenuItem = binding.settingsToolbar.menu.findItem(R.id.settings)

        binding.settingsToolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.settings -> {
                    findNavController().navigate(R.id.action_settingsFragment_to_profileEditFragment)
                    true
                }

                R.id.profile_edit -> {
                    Toast.makeText(requireActivity(), "profile edit clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }

    private fun setupViewState() {
        Glide.with(binding.profileImageView.context)
            .load("https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/10/13/4d8ef85c-ed4f-4f08-843e-19f406616942.jpg")
            .into(binding.profileImageView)

    }
}
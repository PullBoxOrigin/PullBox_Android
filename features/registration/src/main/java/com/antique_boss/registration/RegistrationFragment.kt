package com.antique_boss.registration

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.antique_boss.registration.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private lateinit var completeMenuItem: MenuItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)
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
        binding.registrationToolbar.inflateMenu(R.menu.registration_toolbar_menu)
        completeMenuItem = binding.registrationToolbar.menu.findItem(R.id.registration)
        completeMenuItem.isEnabled = false

        binding.registrationToolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.registration -> {
                    Toast.makeText(requireActivity(), "Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}
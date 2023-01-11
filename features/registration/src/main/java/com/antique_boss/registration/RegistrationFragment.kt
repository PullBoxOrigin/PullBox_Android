package com.antique_boss.registration

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.antique_boss.registration.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val registrationViewModel by navGraphViewModels<RegistrationViewModel>(R.id.registration_nav_graph)

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
        setupDataBinding()
        setupToolbar()
        setupViewListener()
        setupObservers()
    }

    private fun setupDataBinding() {
        binding.lifecycleOwner = this
        binding.registrationViewModel = registrationViewModel
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

    private fun setupViewListener() {
        binding.categorySelectView.setOnClickListener {
            val categories = mutableListOf<String>().apply {
                add("iOS")
                add("Android")
                add("NodeJS")
                add("Spring Boot")
                add("React Native")
                add("Flutter")
                add("Web FrontEnd")
            }
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToCategoryBottomSheetFragment(categories.toTypedArray())
            findNavController().navigate(action)
        }

        //install text watcher to portfolio url edit text
        binding.portfolioUrlInputView.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    registrationViewModel.updatePortfolioUrl(s.toString())
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        //install text watcher to title edit text
        binding.titleInputView.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    registrationViewModel.updateTitle(s.toString())
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

        })

        //install text watcher to main edit text
        binding.mainInputView.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    registrationViewModel.updateMain(s.toString())
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupObservers() {
        registrationViewModel.category.observe(viewLifecycleOwner) {

            registrationViewModel.validateRequiredElement()
        }
        registrationViewModel.portfolioUrl.observe(viewLifecycleOwner) {
            registrationViewModel.validateRequiredElement()
        }
        registrationViewModel.title.observe(viewLifecycleOwner) {
            registrationViewModel.validateRequiredElement()
        }
        registrationViewModel.main.observe(viewLifecycleOwner) {
            registrationViewModel.validateRequiredElement()
        }

        registrationViewModel.registration.observe(viewLifecycleOwner) {
            when(it) {
                true -> completeMenuItem.isEnabled = true
                false -> completeMenuItem.isEnabled = false
            }
        }
    }
}
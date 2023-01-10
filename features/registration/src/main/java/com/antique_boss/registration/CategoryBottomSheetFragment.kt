package com.antique_boss.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.antique_boss.registration.databinding.FragmentCategoryBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CategoryBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentCategoryBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel by navGraphViewModels<RegistrationViewModel>(R.id.registration_nav_graph)
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        setupCategoryRecyclerView()
    }

    private fun setupCategoryRecyclerView() {
        val args: CategoryBottomSheetFragmentArgs by navArgs()
        val categories = args.categories.toList()

        categoryAdapter = CategoryAdapter(categories) { selectedCategory ->
            viewModel.setCategory(selectedCategory)
            findNavController().navigate(R.id.action_categoryBottomSheetFragment_to_registrationFragment)
        }

        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.categoryRecyclerView.adapter = categoryAdapter
    }


}
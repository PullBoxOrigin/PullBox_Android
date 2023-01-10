package com.antique_boss.portfolio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.antique_boss.portfolio.databinding.FragmentPortfolioBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PortfolioFragment : Fragment() {
    private var _binding: FragmentPortfolioBinding? = null
    private val binding get() = _binding!!

    private lateinit var portfolioListAdapter: PortfolioListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_portfolio, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        portfolioListAdapter = PortfolioListAdapter()
        portfolioListAdapter.submitList(listOf(
            "111111111111111111111111111111111111111111111111111111111111111 Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard",
            "222222222222222222222222222222222222222222222222222222222222222 Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard",
            "333333333333333333333333333333333333333333333333333333333333333 Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard",
        ))

        binding.portfolioRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.portfolioRecyclerView.adapter = portfolioListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }




}
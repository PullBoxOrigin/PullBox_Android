package com.antique_boss.portfolio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.antique_boss.portfolio.databinding.FragmentPortfolioDetailBinding

class PortfolioDetailFragment : Fragment() {
    private var _binding: FragmentPortfolioDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_portfolio_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        setupToolbar()
        setupViewListener()
    }

    private fun setupToolbar() {
        binding.portfolioDetailToolbar.setNavigationIcon(R.drawable.ic_back)
        binding.portfolioDetailToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_portfolioDetailFragment_to_portfolioFragment)
        }
    }

    private fun setupViewListener() {
        binding.portfolioDetailCommentsView.setOnClickListener {
            findNavController().navigate(R.id.action_portfolioDetailFragment_to_commentBottomSheetFragment)
        }
    }

}
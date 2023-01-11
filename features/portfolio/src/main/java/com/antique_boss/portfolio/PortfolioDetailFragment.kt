package com.antique_boss.portfolio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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


}
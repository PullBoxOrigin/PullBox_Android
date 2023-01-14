package com.antique_boss.portfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PortfolioViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PortfolioViewModel::class.java)) {
            return PortfolioViewModel() as T
        }
        throw IllegalArgumentException()
    }
}
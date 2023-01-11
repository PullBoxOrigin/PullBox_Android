package com.antique_boss.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {
    private val _category = MutableLiveData<String>("카테고리를 선택해주세요")
    val category: LiveData<String> get() = _category

    private val _portfolioUrl = MutableLiveData<String>()
    val portfolioUrl: LiveData<String> get() = _portfolioUrl

    private val _registration = MutableLiveData<Boolean>(false)
    val registration: LiveData<Boolean> get() = _registration

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _main = MutableLiveData<String>()
    val main: LiveData<String> get() = _main

    fun updateCategory(selectedCategory: String) {
        _category.value = selectedCategory
    }

    fun updatePortfolioUrl(portfolioUrl: String) {
        _portfolioUrl.value = portfolioUrl
    }

    fun updateTitle(title: String) {
        _title.value = title
    }

    fun updateMain(main: String) {
        _main.value = main
    }

    fun validateRequiredElement() {
        _registration.value = (category.value?.isNotEmpty() == true
                && portfolioUrl.value?.isNotEmpty() == true
                && title.value?.isNotEmpty() == true
                && main.value?.isNotEmpty() == true)
    }
}
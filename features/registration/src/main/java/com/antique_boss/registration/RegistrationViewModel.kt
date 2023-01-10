package com.antique_boss.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {
    private val _selectedCategory = MutableLiveData<String>("카테고리를 선택해주세요")
    val selectedCategory: LiveData<String> get() = _selectedCategory

    fun setCategory(selectedCategory: String) {
        _selectedCategory.value = selectedCategory
    }
}
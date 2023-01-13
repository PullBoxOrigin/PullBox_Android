package com.antique_boss.portfolio

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antique_boss.util.preview.Preview
import com.antique_boss.util.preview.PreviewCrawler
import kotlinx.coroutines.launch

class PortfolioViewModel : ViewModel() {
    private val _preview = MutableLiveData<Preview>()
    val preview: LiveData<Preview> get() = _preview

    fun fetchPreview(url: String) {
        viewModelScope.launch {
            _preview.value = PreviewCrawler().getPreview(url)
        }
    }
}
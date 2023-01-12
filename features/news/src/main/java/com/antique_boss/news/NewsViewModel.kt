package com.antique_boss.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antique_boss.util.preview.Preview
import com.antique_boss.util.preview.PreviewCrawler
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val _hotPostPreviews = MutableLiveData<List<Preview>>()
    val hotPostPreviews: LiveData<List<Preview>> get() = _hotPostPreviews

    fun fetchPreviews(urls: List<String>) {
        viewModelScope.launch {
            val previewCrawler = PreviewCrawler()
            val previews = mutableListOf<Preview>()

            for(url in urls) {
                val preview = previewCrawler.getPreview(url)
                previews.add(preview)
            }

            _hotPostPreviews.value = previews
        }
    }
}
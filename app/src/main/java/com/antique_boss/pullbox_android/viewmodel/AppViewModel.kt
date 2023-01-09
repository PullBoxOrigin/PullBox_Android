package com.antique_boss.pullbox_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {
    private val _auth = MutableLiveData<Boolean>()
    val auth: LiveData<Boolean> get() = _auth

    fun successAuthentication() {
        _auth.value = true
    }
}
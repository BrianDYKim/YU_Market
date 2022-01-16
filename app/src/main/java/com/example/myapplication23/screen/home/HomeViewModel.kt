package com.example.myapplication23.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication23.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    // 현재 HomeFragment에 띄워지는 chip의 총 갯수
    var chipQuantity: Int = 0

    override fun fetchData(): Job = viewModelScope.launch {
        // TODO get from repository
    }
}
package com.example.myapplication23.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication23.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    // 현재 homeFragment의 viewPager가 가리키고 있는 페이지 번호
    val homeCurrentPageData = MutableLiveData<Int>()

    override fun fetchData(): Job = viewModelScope.launch {
        // TODO get from repository
    }

}
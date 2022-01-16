package com.example.myapplication23.screen.home.homemain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication23.data.repository.restaurant.HomeRepository
import com.example.myapplication23.model.homelist.TownMarketModel
import com.example.myapplication23.screen.base.BaseViewModel
import com.example.myapplication23.util.LocationData
import com.example.myapplication23.util.LocationState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * HomeMainFragment에서 사용할 ViewModel
 * 기본적으로 Repository에서 Data를 가져온다.
 * @author 정남진
 * @since 2021.01.14
 */
class HomeMainViewModel(
    private val homeRepository: HomeRepository
    // TODO 22.01.12 add item repository
) : BaseViewModel() {

    // TODO 22.01.12 add State?
    private val _marketData = MutableLiveData<List<TownMarketModel>>()
    val marketData: LiveData<List<TownMarketModel>> = _marketData

    override fun fetchData(): Job = viewModelScope.launch {

        // get list after get location data
        if (LocationData.locationStateLiveData.value is LocationState.Success) {
            // TODO sort by distance
            _marketData.value = homeRepository.getAllMarketList()
        }
    }
}
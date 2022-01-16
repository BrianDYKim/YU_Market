package com.example.myapplication23.screen.home.homelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication23.data.repository.restaurant.HomeRepository
import com.example.myapplication23.model.homelist.HomeItemModel
import com.example.myapplication23.model.homelist.category.HomeListCategory
import com.example.myapplication23.model.homelist.TownMarketModel
import com.example.myapplication23.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * HomeListFragment와 HomeListModel 사이에 존재하는 viewModel
 * @author Doyeop Kim
 * @since 2022/01/11
 * @throws None
 * @param homeListCategory: 해당 HomeListModel에 대한 대분류
 * @param homeRepository: HomeListModel에 대해서 데이터를 뽑아와주는 service를 모두 구현해둔 repository 객체
 */
class HomeListViewModel(
    private val homeListCategory: HomeListCategory,
    private val homeRepository: HomeRepository
) : BaseViewModel() {
    val homeListData = when (homeListCategory) {
        HomeListCategory.ALL -> MutableLiveData<List<TownMarketModel>>()
        else -> MutableLiveData<List<HomeItemModel>>()
    }

    override fun fetchData(): Job = viewModelScope.launch {
        homeListData.value = when (homeListCategory) {
            HomeListCategory.ALL -> homeRepository.getAllMarketList()
            HomeListCategory.MART -> homeRepository.findItemsByCategory(HomeListCategory.MART)
            HomeListCategory.FOOD -> homeRepository.findItemsByCategory(HomeListCategory.FOOD)
            HomeListCategory.FASHION -> homeRepository.findItemsByCategory(HomeListCategory.FASHION)
            HomeListCategory.ACCESSORY -> homeRepository.findItemsByCategory(HomeListCategory.ACCESSORY)
            HomeListCategory.SERVICE -> homeRepository.findItemsByCategory(HomeListCategory.SERVICE)
        }
    }
}


//package com.example.myapplication23.screen.home.homelist
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import com.example.myapplication23.data.repository.restaurant.HomeRepository
//import com.example.myapplication23.model.homelist.TownMarketModel
//import com.example.myapplication23.screen.base.BaseViewModel
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
//
//class HomeListViewModel(
//    private val homeCategory: HomeCategory,
//    private val homeRepository: HomeRepository
//) : BaseViewModel() {
//    val homeListData = MutableLiveData<List<TownMarketModel>>()
//
//    override fun fetchData(): Job = viewModelScope.launch {
//        homeListData.value = homeRepository.getAllMarketList()
//    }
//}
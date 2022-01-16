package com.example.myapplication23.data.repository.restaurant

import com.example.myapplication23.model.homelist.HomeItemModel
import com.example.myapplication23.model.homelist.HomeListModel
import com.example.myapplication23.model.homelist.TownMarketModel
import com.example.myapplication23.model.homelist.category.HomeListCategory

/** HomeLiveData에서 필요한 Model의 정보들을 반환하는 메소드들로 구성된 interface
 *
 */
interface HomeRepository {

    // TODO 구현체 필요함
    /** 모든 동네마켓의 리스트를 불러오는 메소드 */
    fun getAllMarketList(): List<TownMarketModel>

    /** HomeListCategory에 따라서 분기하여 해당하는 아이템들을 모두 호출하는 메서드 */
    fun findItemsByCategory(homeListCategory: HomeListCategory): List<HomeItemModel>
}


//package com.example.myapplication23.data.repository.restaurant
//
//import com.example.myapplication23.model.homelist.MarketModel
//import com.example.myapplication23.screen.home.homelist.HomeCategory
//
//interface HomeRepository {
//    fun getList(homeCategory: HomeCategory) : List<MarketModel>
//}
package com.example.myapplication23.data.repository.restaurant

import com.example.myapplication23.data.entity.location.LocationLatLngEntity
import com.example.myapplication23.model.homelist.HomeItemModel
import com.example.myapplication23.model.homelist.HomeListModel
import com.example.myapplication23.model.homelist.TownMarketModel
import com.example.myapplication23.model.homelist.category.HomeListCategory
import com.example.myapplication23.model.homelist.category.HomeListDetailCategory

class DefaultHomeRepository : HomeRepository {

    override fun getAllMarketList(): List<TownMarketModel> {
        // Mocking Data
        val mockList = listOf(
            TownMarketModel(
                0, "쥬얼리 샵", true, LocationLatLngEntity(128.0, 36.0), "https://picsum.photos/200"
            ),
            TownMarketModel(
                1, "영남대 옷가게", true, LocationLatLngEntity(128.0, 36.0), "https://picsum.photos/200"
            ),
            TownMarketModel(
                2, "피자스쿨 영남대점", true, LocationLatLngEntity(128.0, 36.0), "https://picsum.photos/200"
            ),
            TownMarketModel(
                3, "빅마트", false, LocationLatLngEntity(128.0, 36.0), "https://picsum.photos/200"
            ),
            TownMarketModel(
                4,
                "롯데리아 영남대 DT",
                false,
                LocationLatLngEntity(128.0, 36.0),
                "https://picsum.photos/200"
            )
        )

        return mockList
    }

    override fun findItemsByCategory(homeListCategory: HomeListCategory): List<HomeItemModel> {
        return when (homeListCategory) {
            HomeListCategory.FOOD -> listOf(
                HomeItemModel(
                    0, HomeListCategory.FOOD, HomeListDetailCategory.FAST_FOOD,"https://picsum.photos/200",
                    TownMarketModel(
                        4,
                        "롯데리아 영남대 DT",
                        false,
                        LocationLatLngEntity(128.0, 36.0),
                        "https://picsum.photos/200"
                    ),
                    "폴더버거", 5300, 5100, 30, 5, 5
                ),
                HomeItemModel(
                    1, HomeListCategory.FOOD, HomeListDetailCategory.FAST_FOOD,"https://picsum.photos/200",
                    TownMarketModel(
                        4,
                        "롯데리아 영남대 DT",
                        false,
                        LocationLatLngEntity(128.0, 36.0),
                        "https://picsum.photos/200"
                    ),
                    "핫크리스피 버거", 4800, 4500, 30, 5, 5
                ),
                HomeItemModel(
                    2, HomeListCategory.FOOD, HomeListDetailCategory.FAST_FOOD, "https://picsum.photos/200",
                    TownMarketModel(
                        4,
                        "롯데리아 영남대 DT",
                        false,
                        LocationLatLngEntity(128.0, 36.0),
                        "https://picsum.photos/200"
                    ),
                    "불고기버거", 3800, 3800, 30, 5, 5
                )
            )

            HomeListCategory.MART -> listOf(
                HomeItemModel(
                    0, HomeListCategory.MART, HomeListDetailCategory.SNACK_AND_BREAD, "https://picsum.photos/200",
                    TownMarketModel(
                        3,
                        "빅마트",
                        true,
                        LocationLatLngEntity(128.0, 36.0),
                        "https://picsum.photos/200"
                    ),
                    "초코송이", 1500, 800, 5, 5, 5
                ),
                HomeItemModel(
                    1, HomeListCategory.MART, HomeListDetailCategory.WASHING_PRODUCTS,  "https://picsum.photos/200",
                    TownMarketModel(
                        3,
                        "빅마트",
                        true,
                        LocationLatLngEntity(128.0, 36.0),
                        "https://picsum.photos/200"
                    ),
                    "샤프란", 4000, 3500, 3, 3, 2
                ),
                HomeItemModel(
                    2, HomeListCategory.MART, HomeListDetailCategory.MART_EXTRA, "https://picsum.photos/200",
                    TownMarketModel(
                        3,
                        "빅마트",
                        true,
                        LocationLatLngEntity(128.0, 36.0),
                        "https://picsum.photos/200"
                    ),
                    "아이스티 분말", 5000, 4300, 7, 5, 2
                ),
                HomeItemModel(
                    3, HomeListCategory.MART, HomeListDetailCategory.SNACK_AND_BREAD, "https://picsum.photos/200",
                    TownMarketModel(
                        3,
                        "빅마트",
                        true,
                        LocationLatLngEntity(128.0, 36.0),
                        "https://picsum.photos/200"
                    ),
                    "포테이토칩", 1300, 1200, 5, 5, 5
                )
            )

            HomeListCategory.FASHION -> listOf(

            )

            HomeListCategory.ACCESSORY -> listOf(

            )

            HomeListCategory.SERVICE -> listOf(

            )

            HomeListCategory.ETC -> listOf()

            else -> listOf()
        }
    }
}




//package com.example.myapplication23.data.repository.restaurant
//
//import com.example.myapplication23.model.homelist.MarketModel
//import com.example.myapplication23.screen.home.homelist.HomeCategory
//import java.util.*
//
//class DefaultMarketRepository : MarketRepository {
//    override fun getList(homeCategory: HomeCategory): List<MarketModel> {
////        val mockList = listOf(
////            MarketModel(
////                1, "all1", HomeCategory.TOWN_MARKET
////            ),
////            MarketModel(
////                1, "all2", HomeCategory.TOWN_MARKET
////            ),
////            MarketModel(
////                1, "all3", HomeCategory.TOWN_MARKET
////            ),
////            MarketModel(
////                1, "all4", HomeCategory.TOWN_MARKET
////            ),
////            MarketModel(
////                1, "all5", HomeCategory.TOWN_MARKET
////            ),
////            MarketModel(
////                1, "all6", HomeCategory.TOWN_MARKET
////            ),
////            MarketModel(
////                1, "all7", HomeCategory.TOWN_MARKET
////            ),
////
////
////            MarketModel(
////                1, "food1", HomeCategory.FOOD
////            ),
////            MarketModel(
////                1, "food2", HomeCategory.FOOD
////            ),
////            MarketModel(
////                1, "food3", HomeCategory.FOOD
////            ),
////            MarketModel(
////                1, "food4", HomeCategory.FOOD
////            ),
////            MarketModel(
////                1, "food5", HomeCategory.FOOD
////            ),
////            MarketModel(
////                1, "food6", HomeCategory.FOOD
////            ),
////            MarketModel(
////                1, "food7", HomeCategory.FOOD
////            ),
////
////            MarketModel(
////                1, "mart1", HomeCategory.MART
////            ),
////            MarketModel(
////                1, "mart2", HomeCategory.MART
////            ),
////            MarketModel(
////                1, "mart3", HomeCategory.MART
////            ),
////            MarketModel(
////                1, "mart4", HomeCategory.MART
////            ),
////            MarketModel(
////                1, "mart5", HomeCategory.MART
////            ),
////            MarketModel(
////                1, "mart6", HomeCategory.MART
////            ),
////            MarketModel(
////                1, "mart7", HomeCategory.MART
////            ),
////
////
////            MarketModel(
////                1, "service1", HomeCategory.SERVICE
////            ),
////            MarketModel(
////                1, "service2", HomeCategory.SERVICE
////            ),
////            MarketModel(
////                1, "service3", HomeCategory.SERVICE
////            ),
////            MarketModel(
////                1, "service4", HomeCategory.SERVICE
////            ),
////            MarketModel(
////                1, "service5", HomeCategory.SERVICE
////            ),
////            MarketModel(
////                1, "service6", HomeCategory.SERVICE
////            ),
////            MarketModel(
////                1, "service7", HomeCategory.SERVICE
////            ),
////
////
////            MarketModel(
////                1, "fashion1", HomeCategory.FASHION
////            ),
////            MarketModel(
////                1, "fashion2", HomeCategory.FASHION
////            ),
////            MarketModel(
////                1, "fashion3", HomeCategory.FASHION
////            ),
////            MarketModel(
////                1, "fashion4", HomeCategory.FASHION
////            ),
////            MarketModel(
////                1, "fashion5", HomeCategory.FASHION
////            ),
////            MarketModel(
////                1, "fashion6", HomeCategory.FASHION
////            ),
////            MarketModel(
////                1, "fashion7", HomeCategory.FASHION
////            ),
////
////
////            MarketModel(
////                1, "accessory1", HomeCategory.ACCESSORY
////            ),
////            MarketModel(
////                1, "accessory2", HomeCategory.ACCESSORY
////            ),
////            MarketModel(
////                1, "accessory3", HomeCategory.ACCESSORY
////            ),
////            MarketModel(
////                1, "accessory4", HomeCategory.ACCESSORY
////            ),
////            MarketModel(
////                1, "accessory5", HomeCategory.ACCESSORY
////            ),
////            MarketModel(
////                1, "accessory6", HomeCategory.ACCESSORY
////            ),
////            MarketModel(
////                1, "accessory7", HomeCategory.ACCESSORY
////            ),
////        )
////
////
////        return mockList.filter { it.category == homeCategory }
//
//
//        val lists = (1L..10L).map {
//            MarketModel(
//                id = it,
//                title = "$homeCategory$it",
//                category = homeCategory,
//                distance = Random().nextFloat() * 2 // 2km까지
//            )
//        }
//
//        return lists
//
//    }
//}
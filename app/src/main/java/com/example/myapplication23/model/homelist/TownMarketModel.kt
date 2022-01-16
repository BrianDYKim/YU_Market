package com.example.myapplication23.model.homelist

import com.example.myapplication23.data.entity.location.LocationLatLngEntity
import com.example.myapplication23.model.CellType
import com.example.myapplication23.model.homelist.category.HomeListCategory

/**
 * 동네마켓 아이템을 정의해주는 Data Class
 * @author Doyeop Kim
 * @since 2022/01/10
 * @throws None
 * @param homeListCategory: HomeListItem의 대분류. TownMarketModel의 대분류는 Town_Market으로 고정이 되어있다.
 * @param marketName: 동네 가게의 이름
 * @param locationLatLngEntity: 해당 동네마켓의 좌표(직선 거리 계산을 위해 필요한 정보)
 */
data class TownMarketModel(
    override val id: Long,
    val marketName: String,
    val isMarketOpen: Boolean,
    val locationLatLngEntity: LocationLatLngEntity,
    val marketImageUrl: String,
    override val type: CellType = CellType.HOME_CELL,
    override val homeListCategory: HomeListCategory = HomeListCategory.ALL
): HomeListModel(id, type, homeListCategory) {

}

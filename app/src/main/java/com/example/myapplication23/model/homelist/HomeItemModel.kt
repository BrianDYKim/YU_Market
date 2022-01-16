package com.example.myapplication23.model.homelist

import com.example.myapplication23.model.CellType
import com.example.myapplication23.model.homelist.category.HomeListCategory
import com.example.myapplication23.model.homelist.category.HomeListDetailCategory

/**
 * TownMarket을 제외한 HomeListFragment의 아이템을 담는 Model data class이다. 서버 단계에서 찜과 리뷰의 개수를 바로 반환해주는 식으로 적용해야함.
 * @author Doyeop Kim
 * @since 2022/01/11
 * @throws None
 * @param townMarketModel: TownMarketModel -> HomeItemModel과 TownMarketModel과는 서로 연관관계가 존재해야함 (1 : N)
 * @param itemName: String -> 물품 이름
 * @param originalPrice: Int -> 원래 판매 가격
 * @param salePrice: Int -> 실제 판매 가격
 * @param stockQuantity: Int -> 해당 물품에 대한 재고
 * @param likeQuantity: Int -> 해당 물품에 대한 찜의 갯수
 * @param reviewQuantity: Int -> 해당 물품에 대한 리뷰의 갯수
 */
// TODO repository를 통해서 entity -> model 매핑 시 연관관계를 반영해주기
data class HomeItemModel(
    override val id: Long,
    override val homeListCategory: HomeListCategory,
    val homeListDetailCategory: HomeListDetailCategory,
    val itemImageUrl: String,
    val townMarketModel: TownMarketModel,
    val itemName: String,
    val originalPrice: Int,
    val salePrice: Int,
    val stockQuantity: Int,
    val likeQuantity: Int,
    val reviewQuantity: Int,
    override val type: CellType = CellType.HOME_CELL
): HomeListModel(id, type, homeListCategory)

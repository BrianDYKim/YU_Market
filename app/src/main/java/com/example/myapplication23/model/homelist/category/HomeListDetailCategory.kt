package com.example.myapplication23.model.homelist.category

import androidx.annotation.StringRes
import com.example.myapplication23.R

/**
 * HomeList Fragment에 적용되는 세부 카테고리
 * @author Doyeop Kim
 * @since 2022/01/10
 * @throws None
 * @param detailCategoryNameId: 화면에 띄워지는 세부적인 카테고리의 이름
 * @param detailCategoryTypeId: 쿼리 검색에 사용되는 세부적인 카테고리의 이름 (Spring에서 보관할 실제 enum 타입 이름을 지정한다)
 * @param homeListCategory: HomeItem의 대분류를 정의한 파라미터
 */
enum class HomeListDetailCategory(
    @StringRes val detailCategoryNameId: Int,
    @StringRes val detailCategoryTypeId: Int,
    val homeListCategory: HomeListCategory
) {
    JOKBAL_BOSSAM(R.string.home_detail_jokbal_bossam, R.string.home_detail_jokbal_bossam_type, HomeListCategory.FOOD),
    CUTLET_JAPAN_FOOD(R.string.home_detail_cutlet_japan, R.string.home_detail_cutlet_japan_type, HomeListCategory.FOOD),
    CAFE_BREAD(R.string.home_detail_cafe_bread, R.string.home_detail_cafe_bread_type, HomeListCategory.FOOD),
    DESSERT(R.string.home_detail_dessert, R.string.home_detail_dessert_type, HomeListCategory.FOOD),
    FAST_FOOD(R.string.home_detail_fast_food, R.string.home_detail_fast_food_type, HomeListCategory.FOOD),
    CHINA_FOOD(R.string.home_detail_china_food, R.string.home_detail_china_food_type, HomeListCategory.FOOD),
    PIZZA(R.string.home_detail_pizza, R.string.home_detail_pizza_type, HomeListCategory.FOOD),


    SNACK_AND_BREAD(R.string.home_detail_snack_and_bread, R.string.home_detail_snack_and_bread_type, HomeListCategory.MART),
    BEVERAGE_COFFEE_AND_MILK_PRODUCTS(R.string.home_detail_beverage_coffee_and_milk_product, R.string.home_detail_beverage_coffee_and_milk_product_type, HomeListCategory.MART),
    INSATANT_COOK(R.string.home_detail_instant_cook, R.string.home_detail_instant_cook_type, HomeListCategory.MART),
    WASHING_PRODUCTS(R.string.home_detail_washing_products, R.string.home_detail_washing_products_type, HomeListCategory.MART),
    MART_EXTRA(R.string.home_detail_extra, R.string.home_detail_extra_type, HomeListCategory.MART)
}
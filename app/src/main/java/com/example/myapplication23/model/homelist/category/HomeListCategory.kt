package com.example.myapplication23.model.homelist.category

import androidx.annotation.StringRes
import com.example.myapplication23.R

/**
 * HomeItem들의 대분류를 정의한 enum class이다. MainActivity의 Tablayout에서 사용하는 CellType과는 다른 용도로 사용되는 enum class이다.
 * @author Doyeop Kim
 * @param categoryNameId : HomeFragment 단에서 직접적으로 보여지는 이름을 정의한 파라미터
 * @param categoryTypeId : 검색 쿼리에서 사용이 되는 string을 지정한 파라미터
 */
enum class HomeListCategory(
    @StringRes val categoryNameId: Int,
    @StringRes val categoryTypeId: Int
) {
    ALL(R.string.all, R.string.all_type),
    FOOD(R.string.food, R.string.food_type),
    MART(R.string.mart, R.string.mart_type),
    SERVICE(R.string.service, R.string.service_type),
    FASHION(R.string.fashion, R.string.fashion_type),
    ACCESSORY(R.string.accessory, R.string.accessory_type)
}
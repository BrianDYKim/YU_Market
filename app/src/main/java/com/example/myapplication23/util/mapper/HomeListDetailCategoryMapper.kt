package com.example.myapplication23.util.mapper

import com.example.myapplication23.model.homelist.category.HomeListCategory
import com.example.myapplication23.model.homelist.category.HomeListDetailCategory

/**
 * HomeListCategory에 따라서 분기하여 HomeListDetailCategory의 list를 반환하는 mapper
 * @author Doyeop Kim
 * @since 2022/01/13
 * @throws None
 */
object HomeListDetailCategoryMapper {

    /**
     * HomeListCategory의 값에 따라서 detailCategory의 list를 반환해주는 메소드
     */
    fun findDetailCategoriesByCategory(homeListCategory: HomeListCategory): List<HomeListDetailCategory> {
        return HomeListDetailCategory.values().filter {
            it.homeListCategory == homeListCategory
        }
    }
}
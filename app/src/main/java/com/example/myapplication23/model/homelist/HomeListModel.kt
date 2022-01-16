package com.example.myapplication23.model.homelist

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication23.model.CellType
import com.example.myapplication23.model.Model
import com.example.myapplication23.model.homelist.category.HomeListCategory

/**
 * HomeList에서만 사용되는 Model의 추상적 구조를 정의한 클래스
 * @author Doyeop Kim
 * @since 2022/01/10
 * @param id: 해당 아이템의 index
 * @param type: 해당 아이템이 가지는 CellType. Home_Cell로 고정이 되어있다.
 * @param homeListCategory: 해당 아이템이 가지는 대분류를 정의한 파라미터이다.
 */
abstract class HomeListModel(
    override val id: Long,
    override val type: CellType = CellType.HOME_CELL,
    open val homeListCategory: HomeListCategory
) : Model(id, type) {

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<HomeListModel> =
            object : DiffUtil.ItemCallback<HomeListModel>() {

                override fun areItemsTheSame(
                    oldItem: HomeListModel,
                    newItem: HomeListModel
                ): Boolean {
                    return oldItem.id == newItem.id && oldItem.type == newItem.type && oldItem.homeListCategory == newItem.homeListCategory
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: HomeListModel,
                    newItem: HomeListModel
                ): Boolean {
                    return oldItem === newItem
                }
            }
    }
}
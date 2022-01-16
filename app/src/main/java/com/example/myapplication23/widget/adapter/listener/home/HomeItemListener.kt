package com.example.myapplication23.widget.adapter.listener.home

import com.example.myapplication23.model.homelist.HomeItemModel
import com.example.myapplication23.widget.adapter.listener.AdapterListener

/**
 * HomeListFragment의 아이템을 클릭 시 발생하는 이벤트를 정의한 interface
 * @author Doyeop Kim
 * @since 2022/01/11
 * @throws
 */
interface HomeItemListener: AdapterListener {

    fun onClickItem(item: HomeItemModel)
}
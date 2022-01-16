package com.example.myapplication23.widget.adapter.listener.home

import com.example.myapplication23.model.homelist.TownMarketModel
import com.example.myapplication23.widget.adapter.listener.AdapterListener

/**
 * @author Doyeop Kim
 * @since 2022/01/10
 * @throws
 * @description HomeList의 TownMarket을 클릭할 시 발생되는 이벤트를 정의한 인터페이스. RecyclerView의 어댑터를 초기화 할 때 익명함수 형태로 구현하여 전달하면 된다.
 */
interface TownMarketListener: AdapterListener {

    fun onClickItem(townMarketModel: TownMarketModel)
}
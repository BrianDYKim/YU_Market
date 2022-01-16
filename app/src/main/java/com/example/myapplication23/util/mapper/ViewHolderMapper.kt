package com.example.myapplication23.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication23.databinding.ViewholderNearbyMarketBinding
import com.example.myapplication23.model.CellType
import com.example.myapplication23.model.Model
import com.example.myapplication23.screen.base.BaseViewModel
import com.example.myapplication23.util.provider.ResourcesProvider
import com.example.myapplication23.widget.adapter.viewholder.ModelViewHolder
import com.example.myapplication23.widget.adapter.viewholder.homemain.NearbyMarketViewHolder

object ViewHolderMapper {
    @Suppress("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        resourcesProvider: ResourcesProvider
    ): ModelViewHolder<M> {

        val inflater = LayoutInflater.from(parent.context)

        return when (type) {
//            CellType.HOME_LIST_CELL -> {
//                RestaurantViewHolder(
//                    ViewholderRestaurantBinding.inflate(inflater),
//                    viewModel,
//                )
//            }


            // HomeMainFragment에서 근처 마켓을 띄워줄때
            // 사용할 NearbyMarketViewHolder
            CellType.HOME_CELL -> {
                NearbyMarketViewHolder(
                    ViewholderNearbyMarketBinding.inflate(inflater),
                    viewModel,
                    resourcesProvider
                )
            }
        } as ModelViewHolder<M>

    }
}
package com.example.myapplication23.util.mapper

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication23.databinding.ViewholderHomeItemBinding
import com.example.myapplication23.databinding.ViewholderTownMarketBinding
import com.example.myapplication23.model.homelist.HomeListModel
import com.example.myapplication23.model.homelist.category.HomeListCategory
import com.example.myapplication23.screen.base.BaseViewModel
import com.example.myapplication23.util.provider.ResourcesProvider
import com.example.myapplication23.widget.adapter.viewholder.home.HomeItemModelViewHolder
import com.example.myapplication23.widget.adapter.viewholder.home.HomeModelViewHolder
import com.example.myapplication23.widget.adapter.viewholder.home.TownMarketViewHolder

/**
 * HomeListCategory에 따라서 분기하여 ViewHolder를 반환하는 mapper
 * @author Doyeop Kim
 * @since 2022/01/10
 * @throws None
 */
object HomeListViewHolderMapper {

    /**
     * HomeListCategory에 따라서 viewHolder를 매핑시켜주는 메소드
     * @param parent: viewHolder에서 관리할 view
     * @param category: 해당 HomeListModel의 대분류
     * @param viewModel: 해당 HomeListModel을 관리하는 viewModel 객체
     * @param resourcesProvider: resource에 접근하는 것을 돕는 provider 객체
     * @return viewHolder: map 메소드에서 반환해줘야할 viewHolder
     */
    @SuppressLint("UNCHECKED_CAST")
    fun <M : HomeListModel> map(
        parent: ViewGroup,
        category: HomeListCategory,
        viewModel: BaseViewModel,
        resourcesProvider: ResourcesProvider
    ): HomeModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)

        val viewHolder = when (category) {
            HomeListCategory.ALL -> TownMarketViewHolder(
                ViewholderTownMarketBinding.inflate(inflater),
                viewModel,
                resourcesProvider
            )

            else -> HomeItemModelViewHolder(
                ViewholderHomeItemBinding.inflate(inflater),
                viewModel,
                resourcesProvider
            )
        }

        return viewHolder as HomeModelViewHolder<M>
    }
}
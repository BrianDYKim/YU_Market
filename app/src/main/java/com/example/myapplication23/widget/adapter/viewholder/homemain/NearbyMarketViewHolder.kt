package com.example.myapplication23.widget.adapter.viewholder.homemain

import com.example.myapplication23.R
import com.example.myapplication23.databinding.ViewholderNearbyMarketBinding
import com.example.myapplication23.extensions.clear
import com.example.myapplication23.extensions.load
import com.example.myapplication23.model.homelist.TownMarketModel
import com.example.myapplication23.screen.base.BaseViewModel
import com.example.myapplication23.util.provider.ResourcesProvider
import com.example.myapplication23.widget.adapter.listener.AdapterListener
import com.example.myapplication23.widget.adapter.listener.home.TownMarketListener
import com.example.myapplication23.widget.adapter.viewholder.ModelViewHolder
import java.util.*

/**
 * 근처 마켓을 RecyclerView에 보여줄때 사용할 ViewHolder
 * @author 정남진
 * @since 2021.01.14
 */
class NearbyMarketViewHolder(
    private val binding: ViewholderNearbyMarketBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<TownMarketModel>(binding, viewModel, resourcesProvider) {

    override fun reset() {
        binding.nearbyMarketImageView.clear()
    }

    override fun bindViews(model: TownMarketModel, listener: AdapterListener) {
        if (listener is TownMarketListener) {
            binding.root.setOnClickListener {
                listener.onClickItem(model)
            }
        }
    }

    override fun bindData(model: TownMarketModel) {
        super.bindData(model)

        with(binding) {
            nearbyMarketTitle.text = model.marketName

            // 기본은 closed
            // Open일 경우에만 상태를 변경한다.
            if (model.isMarketOpen) {
                nearByMarketState.text = resourcesProvider.getString(R.string.market_open)
                nearByMarketState.setChipBackgroundColorResource(R.color.yellow)
            }

            nearbyMarketDistance.text = "0.1km" // TODO hard coded
            nearbyMarketWorkDay.text = "연중무휴연중무휴연중무휴" // TODO hard coded
            nearbyMarketImageView.load(model.marketImageUrl)
        }
    }
}
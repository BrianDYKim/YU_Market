package com.example.myapplication23.widget.adapter.viewholder.home

import com.example.myapplication23.R
import com.example.myapplication23.databinding.ViewholderTownMarketBinding
import com.example.myapplication23.extensions.clear
import com.example.myapplication23.extensions.load
import com.example.myapplication23.model.homelist.TownMarketModel
import com.example.myapplication23.screen.base.BaseViewModel
import com.example.myapplication23.util.provider.ResourcesProvider
import com.example.myapplication23.widget.adapter.listener.AdapterListener
import com.example.myapplication23.widget.adapter.listener.home.TownMarketListener

/**
 * 동네 마켓에 대한 RecyclerView에 적용할 viewHolder class
 * @author Doyeop Kim
 * @since 2022/01/10
 * @throws None
 * @param binding: 해당 viewHolder에서 관리하는 view의 binding 객체
 * @param viewModel: 해당 Model에 대한 viewModel 객체
 * @param resourcesProvider: resource에 접근을 돕는 provider 객체
 */
class TownMarketViewHolder(
    private val binding: ViewholderTownMarketBinding,
    viewModel: BaseViewModel,
    private val resourcesProvider: ResourcesProvider
): HomeModelViewHolder<TownMarketModel>(binding, viewModel) {

    override fun reset() = with(binding) {
        marketImageView.clear()
    }

    override fun bindData(model: TownMarketModel) {
        super.bindData(model)

        with(binding) {
            // TODO 실제 데이터를 받아오는 경우 데이터가 잘 반영이 되도록 수정
            marketImageView.load(model.marketImageUrl, 16f)
            marketNameText.text = model.marketName // data
            distanceTextView.text = "0.1km" // data
            stockTextView.text = "2개 상품 판매중" // data
            likeCountTextView.text = "1" // data
            reviewCountTextView.text = "1" // data

            likeTextView.text = resourcesProvider.getString(R.string.like)
            reviewTextView.text = resourcesProvider.getString(R.string.review)

            when(model.isMarketOpen) {
                true -> marketOpenStatusView.apply {
                    text = resourcesProvider.getString(R.string.market_open)
                    background = resourcesProvider.getDrawable(R.drawable.viewholder_town_market_open_shape)
                }

                false -> marketOpenStatusView.apply {
                    text = resourcesProvider.getString(R.string.market_closed)
                    background = resourcesProvider.getDrawable(R.drawable.viewholder_town_market_closed_shape)
                }
            }

        }
    }

    override fun bindViews(model: TownMarketModel, listener: AdapterListener) = with(binding) {
        root.setOnClickListener {
            if(listener is TownMarketListener) {
                listener.onClickItem(model)
            }
        }
    }
}
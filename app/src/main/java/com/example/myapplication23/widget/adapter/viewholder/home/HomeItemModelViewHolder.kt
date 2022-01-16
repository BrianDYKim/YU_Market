package com.example.myapplication23.widget.adapter.viewholder.home

import android.view.ViewGroup
import com.example.myapplication23.R
import com.example.myapplication23.databinding.ViewholderHomeItemBinding
import com.example.myapplication23.extensions.clear
import com.example.myapplication23.extensions.load
import com.example.myapplication23.model.homelist.HomeItemModel
import com.example.myapplication23.model.homelist.HomeListModel
import com.example.myapplication23.screen.base.BaseViewModel
import com.example.myapplication23.util.provider.ResourcesProvider
import com.example.myapplication23.widget.adapter.listener.AdapterListener
import com.example.myapplication23.widget.adapter.listener.home.HomeItemListener

/**
 * HomeListFragment의 동네마켓 탭을 제외한 항목에서 사용할 RecyclerView의 viewHolder
 * 식/음료, 편의점/파트, 서비스업종 등등에서 사용해야하는 viewHolder이다.
 * @author Doyeop Kim
 * @since 2022/01/11
 * @throws None
 * @param binding: 해당 view를 담당하는 binding 객체
 * @param viewModel: 해당 Model에 대한 viewModel 객체
 * @param resourcesProvider: resource folder에 접근을 돕는 provider 객체
 */
class HomeItemModelViewHolder(
    private val binding: ViewholderHomeItemBinding,
    viewModel: BaseViewModel,
    private val resourcesProvider: ResourcesProvider
) : HomeModelViewHolder<HomeItemModel>(binding, viewModel) {


    override fun reset() = with(binding) {
        itemImageView.clear()
    }

    override fun bindData(model: HomeItemModel) {
        super.bindData(model)

        val disCountedPrice = model.originalPrice - model.salePrice
        val disCountPercent: Int = 100 * disCountedPrice / model.originalPrice

        with(binding) {
            itemImageView.load(model.itemImageUrl, 0f)
            itemDistanceTextView.text = "0.1"
            distanceUnitTextView.text = resourcesProvider.getString(R.string.distance_unit_kilometer)
            marketNameOfItemTextView.text = model.townMarketModel.marketName
            itemReviewCountTextView.text = model.reviewQuantity.toString()
            itemReviewTextView.text = resourcesProvider.getString(R.string.review)
            itemLikeCountTextView.text = model.likeQuantity.toString()
            itemLikeTextView.text = resourcesProvider.getString(R.string.like)

            itemNameTextView.text = model.itemName
            originPriceTextView.text = resourcesProvider.getString(R.string.home_item_price_format, model.originalPrice)
            disCountPercentTextView.text = resourcesProvider.getString(R.string.home_item_discount_percent_format, disCountPercent, "%")
            salePriceTextView.text = resourcesProvider.getString(R.string.home_item_price_format, model.salePrice)
            itemStockTextView.text = resourcesProvider.getString(R.string.home_item_stock, model.stockQuantity)
        }
    }

    override fun bindViews(model: HomeItemModel, listener: AdapterListener) = with(binding) {
        if (listener is HomeItemListener) {
            root.setOnClickListener {
                listener.onClickItem(model)
            }
        }
    }
}
package com.example.myapplication23.widget.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication23.model.homelist.HomeListModel
import com.example.myapplication23.model.homelist.category.HomeListCategory
import com.example.myapplication23.screen.base.BaseViewModel
import com.example.myapplication23.util.mapper.HomeListViewHolderMapper
import com.example.myapplication23.util.provider.ResourcesProvider
import com.example.myapplication23.widget.adapter.listener.AdapterListener
import com.example.myapplication23.widget.adapter.viewholder.home.HomeModelViewHolder

/**
 * HomeListFragement에 적용이 되는 RecyclerView의 Adapter
 * @author Doyeop Kim
 * @since 2022/01/10
 * @throws None
 */
class HomeModelRecyclerAdapter<M: HomeListModel, VM: BaseViewModel>(
    private var modelList: List<HomeListModel>,
    private val viewModel: VM,
    private val resourcesProvider: ResourcesProvider,
    private val adapterListener: AdapterListener
): ListAdapter<HomeListModel, HomeModelViewHolder<HomeListModel>>(HomeListModel.DIFF_CALLBACK) {

    override fun getItemCount(): Int = modelList.size

    override fun getItemViewType(position: Int): Int = modelList[position].homeListCategory.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeModelViewHolder<HomeListModel> =
        HomeListViewHolderMapper.map(parent, HomeListCategory.values()[viewType], viewModel, resourcesProvider)

    @SuppressLint("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: HomeModelViewHolder<HomeListModel>, position: Int) {
        holder.bindData(modelList[position] as M)
        holder.bindViews(modelList[position] as M, adapterListener)
    }

    override fun submitList(list: List<HomeListModel>?) {
        list?.let {
            modelList = it
        }
        super.submitList(list)
    }
}
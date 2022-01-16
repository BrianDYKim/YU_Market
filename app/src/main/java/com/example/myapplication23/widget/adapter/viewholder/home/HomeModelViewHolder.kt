package com.example.myapplication23.widget.adapter.viewholder.home

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.myapplication23.model.homelist.HomeListModel
import com.example.myapplication23.screen.base.BaseViewModel
import com.example.myapplication23.widget.adapter.listener.AdapterListener

/**
 * HomeModel을 포함하는 RecyclerAdapter에서 사용하는 viewHolder class를 추상화 한 class
 * @author Doyeop Kim
 * @since 2022/01/10
 * @throws None
 * @param binding: 해당 viewHolder의 정보가 저장되는 view의 binding
 * @param viewModel: 해당 HomeListModel을 중재하는 viewModel 객체
 */
abstract class HomeModelViewHolder<M: HomeListModel>(
    binding: ViewBinding,
    protected val viewModel: BaseViewModel
): RecyclerView.ViewHolder(binding.root) {

    // 해당 view에서 띄워주는 image를 날려주는 역할을 수행하는 메소드
    abstract fun reset()

    /**
     * 해당 view에 데이터를 띄워주는 기능을 수행하는 메소드
     * @param model: 해당 view에 띄워지는 Model 객체
     */
    open fun bindData(model: M) {
        reset()
    }

    /**
     * 해당 view에 대한 속성을 정의하는 기능을 수행하는 메소드. 해당 view에 listener를 적용하는 기능을 주로 수행한다
     * @param model: 해당 view에 띄워지는 Model 객체
     * @param listener: 해당 view에 적용되어야하는 Listener.
     */
    abstract fun bindViews(model: M, listener: AdapterListener)
}
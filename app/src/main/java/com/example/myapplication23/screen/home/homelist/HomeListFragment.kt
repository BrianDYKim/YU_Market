package com.example.myapplication23.screen.home.homelist

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication23.databinding.FragmentHomeListBinding
import com.example.myapplication23.model.homelist.HomeItemModel
import com.example.myapplication23.model.homelist.category.HomeListCategory
import com.example.myapplication23.model.homelist.HomeListModel
import com.example.myapplication23.model.homelist.TownMarketModel
import com.example.myapplication23.screen.base.BaseFragment
import com.example.myapplication23.util.provider.ResourcesProvider
import com.example.myapplication23.widget.adapter.HomeModelRecyclerAdapter
import com.example.myapplication23.widget.adapter.listener.home.HomeItemListener
import com.example.myapplication23.widget.adapter.listener.home.TownMarketListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeListFragment : BaseFragment<HomeListViewModel, FragmentHomeListBinding>() {

    private val resourcesProvider by inject<ResourcesProvider>()

    override fun getViewBinding(): FragmentHomeListBinding =
        FragmentHomeListBinding.inflate(layoutInflater)

    private val homeListCategory: HomeListCategory by lazy {
        arguments?.getSerializable(HOME_CATEGORY_KEY) as HomeListCategory
    }

    override val viewModel by viewModel<HomeListViewModel> {
        parametersOf(homeListCategory)
    }

    /**
     * adapter의 경우 homeListCategory에 따라서 띄워줘야할 activity의 모양이 다르다.
     * 따라서, homeListCategory에 따라서 adapter를 달리해주는 것이 옳다.
     */
    private val adapter by lazy {
        HomeModelRecyclerAdapter<HomeListModel, HomeListViewModel>(
            listOf(), viewModel, resourcesProvider,
            adapterListener = when (homeListCategory) {
                HomeListCategory.TOWN_MARKET -> {
                    object : TownMarketListener {
                        override fun onClickItem(townMarketModel: TownMarketModel) = Unit
                    }
                }

                else -> {
                    object : HomeItemListener {
                        override fun onClickItem(item: HomeItemModel) {
                            // TODO startActivity
                            when(homeListCategory) {
                                HomeListCategory.FOOD -> Toast.makeText(requireContext(), "Food!", Toast.LENGTH_SHORT).show()
                                HomeListCategory.MART -> Toast.makeText(requireContext(), "Mart!", Toast.LENGTH_SHORT).show()
                                HomeListCategory.SERVICE -> Toast.makeText(requireContext(), "Service!", Toast.LENGTH_SHORT).show()
                                HomeListCategory.FASHION-> Toast.makeText(requireContext(), "Fashion!", Toast.LENGTH_SHORT).show()
                                HomeListCategory.ACCESSORY -> Toast.makeText(requireContext(), "Accessory!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        )
    }

    /**
     * View에 대한 초기화 작업을 여기서 수행한다.
     * 홈에 띄워줄 카테고리의 종류에 따라서 띄워줄 viewHolder가 다르기 때문에 when문을 이용하여 category에 따라서 adapter를 다르게 적용해야함
     */
    override fun initViews() = with(binding) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@HomeListFragment.context)
    }

    override fun observeData() = with(viewModel) {
        homeListData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        const val HOME_CATEGORY_KEY = "HomeCategoryKey"

        fun newInstance(homeCategory: HomeListCategory): HomeListFragment {
            val bundle = Bundle().apply {
                putSerializable(HOME_CATEGORY_KEY, homeCategory)
            }

            return HomeListFragment().apply {
                arguments = bundle
            }
        }
    }
}


//package com.example.myapplication23.screen.home.homelist
//
//import android.os.Bundle
//import android.widget.Toast
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.myapplication23.databinding.FragmentHomeListBinding
//import com.example.myapplication23.model.homelist.TownMarketModel
//import com.example.myapplication23.screen.base.BaseFragment
//import com.example.myapplication23.widget.adapter.ModelRecyclerAdapter
//import com.example.myapplication23.widget.adapter.listener.home.TownMarketListener
//import org.koin.android.viewmodel.ext.android.viewModel
//import org.koin.core.parameter.parametersOf
//
//class HomeListFragment : BaseFragment<HomeListViewModel, FragmentHomeListBinding>() {
//    override fun getViewBinding(): FragmentHomeListBinding =
//        FragmentHomeListBinding.inflate(layoutInflater)
//
//    private val homeCategory: HomeCategory by lazy {
//        arguments?.getSerializable(HOME_CATEGORY_KEY) as HomeCategory
//    }
//
//    override val viewModel by viewModel<HomeListViewModel> {
//        parametersOf(homeCategory)
//    }
//
//    private val adapter by lazy {
//        ModelRecyclerAdapter<TownMarketModel, HomeListViewModel>(
//            listOf(),
//            viewModel,
//            object : TownMarketListener {
//                override fun onClickItem(townMarketModel: TownMarketModel) {
//                    showMessage(townMarketModel.toString())
//                }
//            }
//        )
//    }
//
//    override fun initViews() = with(binding) {
//        restaurantRecyclerView.adapter = adapter
//        restaurantRecyclerView.layoutManager = LinearLayoutManager(this@HomeListFragment.context)
//
//        testView.text = context?.getText(homeCategory.categoryNameId)
//    }
//
//    private fun showMessage(message: String) =
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//
//
//    override fun observeData() = with(viewModel) {
//        homeListData.observe(viewLifecycleOwner) {
//            adapter.submitList(it)
//        }
//    }
//
//    companion object {
//        const val HOME_CATEGORY_KEY = "HomeCategoryKey"
//
//        fun newInstance(homeCategory: HomeCategory): HomeListFragment {
//            val bundle = Bundle().apply {
//                putSerializable(HOME_CATEGORY_KEY, homeCategory)
//            }
//
//            return HomeListFragment().apply {
//                arguments = bundle
//            }
//        }
//    }
//}
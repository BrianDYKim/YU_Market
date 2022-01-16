package com.example.myapplication23.screen.home

import android.util.Log
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication23.R
import com.example.myapplication23.databinding.FragmentHomeBinding
import com.example.myapplication23.model.homelist.category.HomeListCategory
import com.example.myapplication23.screen.base.BaseFragment
import com.example.myapplication23.screen.home.homelist.HomeListFragment
import com.example.myapplication23.util.LocationData
import com.example.myapplication23.util.LocationState
import com.example.myapplication23.util.mapper.HomeListDetailCategoryMapper
import com.example.myapplication23.widget.adapter.HomeListFragmentPagerAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment
    : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private lateinit var viewPagerAdapter: HomeListFragmentPagerAdapter

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
        initChipGroup() // ChipGroup의 초기화 작업 진행
    }

    override fun observeData() = with(binding) {
        LocationData.locationStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LocationState.Success -> {
                    initViewPager()
                }
            }
        }
    }

    private fun initViewPager() = with(binding) {
        orderChipGroup.isVisible = true

        if (::viewPagerAdapter.isInitialized.not()) {
            val homeCategory = HomeListCategory.values()

            val homeListFragmentList = homeCategory.map {
//                RestaurantListFragment.newInstance(it, locationLatLng)
                HomeListFragment.newInstance(it)
            }
            viewPagerAdapter = HomeListFragmentPagerAdapter(
                this@HomeFragment,
                homeListFragmentList
            )
            viewPager.adapter = viewPagerAdapter

            viewPager.offscreenPageLimit = homeCategory.size

            /**
             * viewPager의 변화 감지 -> viewModel에서의 liveData 변화 -> ObserveData를 통해서 chipGroup의 구성을 변화시킨다.
             * @author Doyeop Kim
             */
            viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    Log.d("listener", viewPager.currentItem.toString())
                    viewModel.homeCurrentPageData.value = viewPager.currentItem
                }
            })

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.setText(HomeListCategory.values()[position].categoryNameId)
            }.attach()
        }


//        if (locationLatLng != viewPagerAdapter.locationLatLng) {
//            viewPagerAdapter.locationLatLng = locationLatLng
//            viewPagerAdapter.fragmentList.forEach {
//                it.viewModel.setLocationLatLng(locationLatLng)
//            }
//        }
    }

    /**
     * ChipGroup을 초기화하는 메소드. ChipGroup은 최초에 동네마켓을 기준으로 초기화를 진행한다
     * @author Doyeop Kim
     * @since 2022/01/12
     */
    private fun initChipGroup() {
        changeChipGroup(HomeListCategory.ALL)
    }

    /**
     *
     * @author Doyeop Kim
     * @since 2022/01/12
     * @param homeListCategory: HomeListCategory -> 현재 viewPager의 fragment가 가리키는 homeListCategory
     */
    private fun changeChipGroup(homeListCategory: HomeListCategory) = with(binding.orderChipGroup) {

        val homeListDetailCategories = HomeListDetailCategoryMapper.findDetailCategoriesByCategory(homeListCategory)
        val categoryQuantity = homeListDetailCategories.size

        children.forEachIndexed { index, view ->

            if(view is Chip) {

                when(index) {
                    0 -> {

                    }

                    in 1..categoryQuantity -> {

                    }

                    else -> {

                    }

                }
            }
        }
    }

    companion object {
        const val TAG = "HomeFragment"

        fun newInstance() = HomeFragment()
    }
}


//package com.example.myapplication23.screen.home
//
//
//import android.location.Location
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.view.isVisible
//import com.example.myapplication23.databinding.FragmentHomeBinding
//import com.example.myapplication23.screen.base.BaseFragment
//import com.example.myapplication23.screen.home.homelist.HomeCategory
//import com.example.myapplication23.screen.home.homelist.HomeListFragment
//import com.example.myapplication23.screen.home.homemain.HomeMainFragment
//import com.example.myapplication23.util.LocationData
//import com.example.myapplication23.util.LocationState
//import com.example.myapplication23.widget.adapter.HomeListFragmentPagerAdapter
//import com.google.android.material.tabs.TabLayoutMediator
//import org.koin.android.viewmodel.ext.android.viewModel
//
//
//class HomeFragment
//    : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
//
//    private lateinit var viewPagerAdapter: HomeListFragmentPagerAdapter
//
//    override val viewModel by viewModel<HomeViewModel>()
//
//    override fun getViewBinding(): FragmentHomeBinding =
//        FragmentHomeBinding.inflate(layoutInflater)
//
//    override fun initViews() {
//        super.initViews()
//    }
//
//    override fun observeData() = with(binding) {
////        initViewPager()
//        LocationData.locationStateLiveData.observe(viewLifecycleOwner) {
//            when (it) {
//                is LocationState.Success -> {
//                    Log.d("TAG", "initViews: SUCCESS222")
//                    initViewPager()
//                }
//            }
//        }
//    }
//
//
//    private fun initViewPager() = with(binding) {
//        Log.d("TAG", "initviewpager")
//        orderChipGroup.isVisible = true
//
//        if (::viewPagerAdapter.isInitialized.not()) {
//            val homeCategory = HomeCategory.values()
//
//            val homeListFragmentList = homeCategory.map {
////                RestaurantListFragment.newInstance(it, locationLatLng)
//                HomeListFragment.newInstance(it)
//            }
//
//
//            viewPagerAdapter = HomeListFragmentPagerAdapter(
//                this@HomeFragment,
//                homeListFragmentList,
////                locationLatLng
//            )
//
//            viewPager.adapter = viewPagerAdapter
//
//            viewPager.offscreenPageLimit = homeCategory.size
//
//            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//                tab.setText(HomeCategory.values()[position].categoryNameId)
//            }.attach()
//        }
//
//
////        if (locationLatLng != viewPagerAdapter.locationLatLng) {
////            viewPagerAdapter.locationLatLng = locationLatLng
////            viewPagerAdapter.fragmentList.forEach {
////                it.viewModel.setLocationLatLng(locationLatLng)
////            }
////        }
//    }
//
//
//    companion object {
//        const val TAG = "HomeFragment"
//
//        fun newInstance() : HomeFragment {
//            return HomeFragment().apply {
//
//            }
//        }
//    }
//}
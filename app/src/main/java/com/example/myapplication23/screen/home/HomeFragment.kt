package com.example.myapplication23.screen.home

import android.util.Log
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication23.R
import com.example.myapplication23.databinding.FragmentHomeBinding
import com.example.myapplication23.model.homelist.category.HomeListCategory
import com.example.myapplication23.model.homelist.category.HomeListDetailCategory
import com.example.myapplication23.screen.base.BaseFragment
import com.example.myapplication23.screen.home.homelist.HomeListFragment
import com.example.myapplication23.util.LocationData
import com.example.myapplication23.util.LocationState
import com.example.myapplication23.util.mapper.HomeListDetailCategoryMapper
import com.example.myapplication23.widget.adapter.HomeListFragmentPagerAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private val homeListCategories = HomeListCategory.values()

    private lateinit var viewPagerAdapter: HomeListFragmentPagerAdapter

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
        initChipGroup() // ChipGroup의 초기화 작업 진행
    }

    /**
     * (1차 수정) viewPager의 최초 view 설정 내용 추가
     * @author Doyeop Kim
     * @since 2022/01/17
     */
    override fun observeData() = with(binding) {
        LocationData.locationStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LocationState.Success -> {
                    initViewPager()
                    viewPager.post {
                        viewPager.currentItem = HomeListCategory.values().indexOf(InitialViewPagerFragmentObserver.homeListCategory)
                    }
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
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    Log.d("listener", viewPager.currentItem.toString())
                    changeChipGroup(homeListCategories[viewPager.currentItem])
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
        changeChipGroup(HomeListCategory.TOWN_MARKET)
    }

    /**
     *
     * @author Doyeop Kim
     * @since 2022/01/12
     * @param homeListCategory: HomeListCategory -> 현재 viewPager의 fragment가 가리키는 homeListCategory
     */
    private fun changeChipGroup(homeListCategory: HomeListCategory) = with(binding.orderChipGroup) {

        val homeListDetailCategories =
            HomeListDetailCategoryMapper.findDetailCategoriesByCategory(homeListCategory)
        val categoryQuantity = homeListDetailCategories.size

        // 칩의 개수를 동적으로 할당하여 관리한다.
        if (viewModel.chipQuantity < categoryQuantity) {
            for (i in 1..(categoryQuantity - viewModel.chipQuantity)) {
                val chip = layoutInflater.inflate(R.layout.home_chip, this, false) as Chip
                addView(chip)
            }
            viewModel.chipQuantity = categoryQuantity
        }

        children.forEachIndexed { index, view ->

            if (view is Chip) {

                when (index) {
                    0 -> changeChip(
                        view,
                        homeListDetailCategories[index],
                        isVisible = true,
                        isChecked = true
                    )
                    in 1 until categoryQuantity -> changeChip(
                        view,
                        homeListDetailCategories[index],
                        isVisible = true,
                        isChecked = false
                    )
                    else -> changeChip(view, null, isVisible = false, isChecked = false)
                }
            }
        }
    }

    /**
     * 칩의 구성을 변환시키는 메소드
     * @author Doyeop Kim
     * @since 2022/01/13
     * @param chip: chip view
     * @param content: chip의 text에 들어가야할 내용물
     * @param isVisible: chip이 보여지는지 여부를 결정하는 파라미터. default value는 true로 설정이 되어있다.
     * @param isChecked: 현재 chip이 체크가 되어있는 상황인지를 담아둔 boolean type의 변수. default value는 false로 설정이 되어있다.
     */
    private fun changeChip(
        chip: Chip, homeListDetailCategory: HomeListDetailCategory?, isVisible: Boolean = true,
        isChecked: Boolean = false
    ) {
        // TODO click에 대한 반응 구현
        homeListDetailCategory?.let {
            chip.text = getString(homeListDetailCategory.detailCategoryNameId)
        }
        chip.isVisible = isVisible
        chip.isChecked = isChecked
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
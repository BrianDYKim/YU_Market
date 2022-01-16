package com.example.myapplication23.screen.home.homemain

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication23.R
import com.example.myapplication23.databinding.FragmentHomeMainBinding
import com.example.myapplication23.model.homelist.TownMarketModel
import com.example.myapplication23.screen.base.BaseFragment
import com.example.myapplication23.screen.home.HomeFragment
import com.example.myapplication23.screen.home.homelist.HomeCategory
import com.example.myapplication23.util.LocationData
import com.example.myapplication23.util.LocationState
import com.example.myapplication23.util.provider.ResourcesProvider
import com.example.myapplication23.widget.adapter.ModelRecyclerAdapter
import com.example.myapplication23.widget.adapter.listener.home.TownMarketListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * BottomNavigationBar에서 Home을 선택하면 띄워줄 Fragment
 * @author 정남진
 * @since 2021.01.14
 */
class HomeMainFragment
    : BaseFragment<HomeMainViewModel, FragmentHomeMainBinding>(),
    AdapterView.OnItemSelectedListener {

    override val viewModel by viewModel<HomeMainViewModel>()

    private val resourcesProvider by inject<ResourcesProvider>()

    override fun getViewBinding(): FragmentHomeMainBinding =
        FragmentHomeMainBinding.inflate(layoutInflater)

    override fun observeData() {
        // marketData가 변경되면 update
        viewModel.marketData.observe(viewLifecycleOwner) {
            nearbyMarketAdapter.submitList(it)
        }

        // 위치 정보를 불러오고 fetchData
        LocationData.locationStateLiveData.observe(viewLifecycleOwner) {
            // get list after get location
            if (it is LocationState.Success) {
                viewModel.fetchData()
            }
        }
    }


    private val nearbyMarketAdapter by lazy {
        ModelRecyclerAdapter<TownMarketModel, HomeMainViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            object : TownMarketListener {
                // RecyclerView의 Item을 클릭할때
                override fun onClickItem(townMarketModel: TownMarketModel) {
                    Toast.makeText(context, townMarketModel.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    override fun initViews() {

        super.initViews()

        // Spinner의 Adapter에 사용할 List
        // 마켓의 업종을 나타내는 String
        val adapterList = HomeCategory.values().map {
            getString(it.categoryNameId)
        }

        with(binding) {

            // 새로운 할인상품에 사용할 Spinner의 Adapter 설정
            newSaleItemSpinner.adapter = ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                adapterList
            )

            // 현재 Fragment가 AdapterView.OnItemSelectedListener를 상속받아
            // Item이 선택됐을때 무엇을 할지 정의함
            newSaleItemSpinner.onItemSelectedListener = this@HomeMainFragment

            // 근처 마켓 RecyclerView 설정
            nearbyMarketRecyclerView.adapter = nearbyMarketAdapter
            
            // 한줄에 2개씩 띄우도록 설정(spanCount)
            nearbyMarketRecyclerView.layoutManager = GridLayoutManager(
                requireContext(),
                2,
                GridLayoutManager.VERTICAL,
                false
            )

            // 더보기를 누르면 마켓을 List로 띄워주는 Fragment로 이동
            showMoreTextView.setOnClickListener {
                showListFragment()
            }
        }
    }

    /**
     * 더보기를 클릭하면 마켓을 RecyclerView로
     * 띄워주는 Fragment(현재 HomeFragment)를 띄워준다.
     * MainActivity의 showFragment와 같다.
     */
    private fun showListFragment() {
        val fragmentManager = parentFragmentManager
        val fragmentFound = fragmentManager.findFragmentByTag(HomeFragment.TAG)

        fragmentManager.fragments.forEach {
            fragmentManager.beginTransaction().hide(it).commitAllowingStateLoss()
        }

        fragmentFound?.let {
            fragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        } ?: kotlin.run {
            fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, HomeFragment.newInstance(), HomeFragment.TAG)
                .commitAllowingStateLoss()
        }
    }

    /**
     * Spinner에서 Item을 선택할때 동작 설정
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // TODO 22.01.12 add list sort
        when (parent!!.getItemAtPosition(position).toString()) {
            getString(R.string.all) -> {

            }

            getString(R.string.food) -> {

            }

            getString(R.string.mart) -> {

            }

            getString(R.string.service) -> {

            }

            getString(R.string.fashion) -> {

            }

            getString(R.string.accessory) -> {

            }

            getString(R.string.etc) -> {

            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


    companion object {
        const val TAG = "HomeMainFragment"

        fun newInstance() = HomeMainFragment()
    }
}
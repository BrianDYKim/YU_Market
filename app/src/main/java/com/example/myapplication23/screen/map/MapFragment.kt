package com.example.myapplication23.screen.map

import com.example.myapplication23.databinding.FragmentMapBinding
import com.example.myapplication23.screen.base.BaseFragment
import com.example.myapplication23.screen.like.LikeFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment<MapViewModel, FragmentMapBinding>() {
    override val viewModel by viewModel<MapViewModel>()

    override fun getViewBinding(): FragmentMapBinding =
        FragmentMapBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    companion object {
        const val TAG = "MapFragment"

        fun newInstance() = MapFragment()
    }
}
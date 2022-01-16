package com.example.myapplication23.di

import com.example.myapplication23.data.repository.map.DefaultMapRepository
import com.example.myapplication23.data.repository.map.MapRepository
import com.example.myapplication23.data.repository.restaurant.DefaultHomeRepository
import com.example.myapplication23.data.repository.restaurant.HomeRepository
import com.example.myapplication23.model.homelist.category.HomeListCategory
import com.example.myapplication23.screen.MainViewModel
import com.example.myapplication23.screen.home.homelist.HomeCategory
import com.example.myapplication23.screen.home.homelist.HomeListViewModel
import com.example.myapplication23.screen.home.HomeViewModel
import com.example.myapplication23.screen.home.homemain.HomeMainViewModel
import com.example.myapplication23.screen.like.LikeViewModel
import com.example.myapplication23.screen.map.MapViewModel
import com.example.myapplication23.screen.myinfo.MyInfoViewModel
import com.example.myapplication23.screen.orderlist.OrderListViewModel
import com.example.myapplication23.util.provider.DefaultResourcesProvider
import com.example.myapplication23.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel() }

    factory { (homeListCategory: HomeListCategory) ->
        HomeListViewModel(homeListCategory, get())
    }

    viewModel { MainViewModel(get()) }
    viewModel { LikeViewModel() }
    viewModel { MapViewModel() }
    viewModel { MyInfoViewModel() }
    viewModel { OrderListViewModel() }

    single<HomeRepository> { DefaultHomeRepository() }

    single { buildOkHttpClient() }
    single { provideGsonConverterFactory() }

    single(named("map")) { provideMapRetrofit(get(), get()) }
    single { provideMapApiService(get(qualifier = named("map"))) }

    single<MapRepository> { DefaultMapRepository(get(), get()) }

    single { Dispatchers.IO }
    single { Dispatchers.Main }

    single<ResourcesProvider> { DefaultResourcesProvider(androidContext()) }

    // 22.01.10 added
    viewModel { HomeMainViewModel(get()) }
}
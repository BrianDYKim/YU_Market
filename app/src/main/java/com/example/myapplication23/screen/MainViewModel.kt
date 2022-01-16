package com.example.myapplication23.screen

import androidx.lifecycle.viewModelScope
import com.example.myapplication23.R
import com.example.myapplication23.data.entity.location.LocationLatLngEntity
import com.example.myapplication23.data.entity.location.MapSearchInfoEntity
import com.example.myapplication23.data.repository.map.MapRepository
import com.example.myapplication23.screen.base.BaseViewModel
import com.example.myapplication23.util.LocationData
import com.example.myapplication23.util.LocationState
import kotlinx.coroutines.launch

class MainViewModel(
    private val mapRepository: MapRepository
) : BaseViewModel() {

//    val mainStateLiveData = MutableLiveData<MainState>(MainState.Uninitialized)

    fun getMapSearchInfo() {

    }

    fun getReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity
    ) = viewModelScope.launch {

        val currentLocation = locationLatLngEntity

        val addressInfo = mapRepository.getReverseGeoInformation(locationLatLngEntity)

        addressInfo?.let { addressInfo ->

//            val mapSearchInfoEntityResult = MapSearchInfoEntity(
//                fullAddress = addressInfo.fullAddress ?: "주소 정보 없음",
//                name = addressInfo.buildingName ?: "주소 정보 없음",
//                locationLatLng = currentLocation
//            )


            LocationData.locationStateLiveData.value = LocationState.Success(
                mapSearchInfoEntity = MapSearchInfoEntity(
                    fullAddress = addressInfo.fullAddress ?: "주소 정보 없음",
                    name = addressInfo.buildingName ?: "주소 정보 없음",
                    locationLatLng = currentLocation),
                isLocationSame = false
            )

//            LocationData.locationStateLiveData.value = LocationState.Success(mapSearchInfoEntityResult)

        } ?: kotlin.run {
//            mainStateLiveData.value = MainState.Error(
//                R.string.cannot_load_address_info
//            )

            LocationData.locationStateLiveData.value = LocationState.Error(
                R.string.cannot_load_address_info
            )
        }
    }
}
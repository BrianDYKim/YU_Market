package com.example.myapplication23.screen

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.example.myapplication23.R
import com.example.myapplication23.data.entity.location.LocationLatLngEntity
import com.example.myapplication23.databinding.ActivityMainBinding
import com.example.myapplication23.screen.base.BaseActivity
import com.example.myapplication23.screen.home.HomeFragment
import com.example.myapplication23.screen.home.homemain.HomeMainFragment
import com.example.myapplication23.screen.like.LikeFragment
import com.example.myapplication23.screen.map.MapFragment
import com.example.myapplication23.screen.myinfo.MyInfoFragment
import com.example.myapplication23.screen.orderlist.OrderListFragment
import com.example.myapplication23.util.LocationData
import com.example.myapplication23.util.LocationState
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity
    : BaseActivity<MainViewModel, ActivityMainBinding>(),
    NavigationBarView.OnItemSelectedListener {

    companion object {
        val locationPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val responsePermissions = permissions.entries.filter {
                it.key in locationPermissions
            }

            if (responsePermissions.filter { it.value == true }.size == locationPermissions.size) {
                setLocationListener()
            } else {
                Toast.makeText(this, "no", Toast.LENGTH_SHORT).show()
            }
        }

//    private val changeLocationLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { results ->
//            result.data?.getParcelableExtra<MapSearchInfoEntity>(MY_LOCATION_KEY)?.let { myLocationInfo ->
//                viewModel.loadReverseGeoInformation(myLocationInfo.locationLatLng)
//            }
//        }

    private lateinit var locationManager: LocationManager
    private lateinit var myLocationListener: MyLocationListener

    override val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun observeData() = with(binding) {
//        viewModel.mainStateLiveData
        LocationData.locationStateLiveData.observe(this@MainActivity) {
            when (it) {
                is LocationState.Uninitialized -> {
                    getMyLocation()
                }

                is LocationState.Loading -> {

                }

                is LocationState.Success -> {
                    locationLoading.isGone = true
                    locationTitleTextView.text = it.mapSearchInfoEntity.fullAddress
                }

                is LocationState.Error -> {
                    locationTitleTextView.text = getString(it.errorMessage)
                }
            }
        }
    }

    override fun initViews() = with(binding) {

        bottomNav.setOnItemSelectedListener(this@MainActivity)

        // show home fragment
        showFragment(HomeMainFragment.newInstance(), HomeMainFragment.TAG)

        locationTitleTextView.setOnClickListener {
            viewModel.getMapSearchInfo()?.let { mapInfo ->
//                changeLocationLauncher.launch(
//                    MyLocationActivity.newIntent(
//                        requireContext(), mapInfo
//                    )
//                )
            }
        }

    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val fragmentFound = supportFragmentManager.findFragmentByTag(tag)

        supportFragmentManager.fragments.forEach {
            supportFragmentManager.beginTransaction().hide(it).commitAllowingStateLoss()
        }

        fragmentFound?.let {
            supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment, tag)
                .commitAllowingStateLoss()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                showFragment(HomeMainFragment.newInstance(), HomeMainFragment.TAG)
                return true
            }

            R.id.order_list -> {
                showFragment(OrderListFragment.newInstance(), OrderListFragment.TAG)
                return true
            }

            R.id.like -> {
                showFragment(LikeFragment.newInstance(), LikeFragment.TAG)
                return true
            }

            R.id.map -> {
                showFragment(MapFragment.newInstance(), MapFragment.TAG)
                return true
            }

            R.id.my_info -> {
                showFragment(MyInfoFragment.newInstance(), MyInfoFragment.TAG)
                return true
            }
        }

        return false
    }

    private fun getMyLocation() {
        if (::locationManager.isInitialized.not()) {
            locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
        val isGpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isGpsEnable) {
            permissionLauncher.launch(locationPermissions)
        }
    }

    @Suppress("MissingPermission")
    private fun setLocationListener() {
        val minTime: Long = 1500
        val minDistance = 100f

        if (::myLocationListener.isInitialized.not()) {
            myLocationListener = MyLocationListener()
        }

        with(locationManager) {
            requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime, minDistance, myLocationListener
            )

            requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                minTime, minDistance, myLocationListener
            )
        }
    }

    inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
//            binding.locationTitleTextView.text = "${location.latitude}, ${location.longitude}"
            viewModel.getReverseGeoInformation(
                LocationLatLngEntity(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            )

            removeLocationListener()
        }

        private fun removeLocationListener() {
            if (::locationManager.isInitialized && ::myLocationListener.isInitialized) {
                locationManager.removeUpdates(myLocationListener)
            }
        }
    }
}
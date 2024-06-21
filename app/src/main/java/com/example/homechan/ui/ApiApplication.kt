package com.example.homechan.ui

import android.app.Application
import com.example.homechan.data.remote.DeviceRemoteDataSource
import com.example.homechan.data.remote.api.RetrofitClient
import com.example.homechan.data.repository.DeviceRepository


class ApiApplication : Application() {



    private val deviceRemoteDataSource: DeviceRemoteDataSource
        get() = DeviceRemoteDataSource(RetrofitClient.deviceService)



    val deviceRepository: DeviceRepository
        get() = DeviceRepository(deviceRemoteDataSource, this)
}
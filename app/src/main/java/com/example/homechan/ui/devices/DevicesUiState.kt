package com.example.homechan.ui.devices

import com.example.homechan.data.model.Device
import com.example.homechan.data.model.Error


data class DevicesUiState(
    val isFetching: Boolean = false,
    val error: Error? = null,
    val devices: List<Device> = emptyList()
)
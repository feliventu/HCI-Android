package com.example.homechan.ui.devices

import com.example.homechan.data.model.Ac
import com.example.homechan.data.model.Error


data class AcUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    var currentDevice: Ac? = null
)

val AcUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading
package com.example.homechan.ui.devices

import com.example.homechan.data.model.Blinds
import com.example.homechan.data.model.Error


data class BlindsUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    var currentDevice: Blinds? = null
)

val BlindsUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading
package com.example.homechan.ui.devices

import com.example.homechan.data.model.Alarm
import com.example.homechan.data.model.Error

data class AlarmUiState(
    val loading:Boolean = false,
    val error: Error? = null,
    var currentDevice: Alarm? = null
)

val AlarmUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading
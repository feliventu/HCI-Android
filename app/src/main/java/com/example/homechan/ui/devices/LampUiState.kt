package com.example.homechan.ui.devices

import com.example.homechan.data.model.Error
import com.example.homechan.data.model.Lamp


data class LampUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    var currentDevice: Lamp? = null
)

val LampUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading
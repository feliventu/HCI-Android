package com.example.homechan.ui.devices

import com.example.homechan.data.model.Error
import com.example.homechan.data.model.Speaker

data class SpeakerUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    var currentDevice: Speaker? = null
)

val SpeakerUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading

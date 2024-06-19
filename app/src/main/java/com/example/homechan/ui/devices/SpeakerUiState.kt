package com.example.homechan.ui.devices

import com.example.homechan.data.model.Error
import com.example.homechan.data.model.Speaker

data class SpeakerUIState(
    val loading: Boolean = false,
    val error: Error? = null,
    var currentDevice: Speaker? = null
)

val SpeakerUIState.canExecuteAction: Boolean get() = currentDevice != null && !loading
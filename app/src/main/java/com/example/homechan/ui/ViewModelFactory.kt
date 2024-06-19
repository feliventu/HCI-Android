package com.example.homechan.ui

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.homechan.data.repository.DeviceRepository
import com.example.homechan.ui.devices.AcViewModel
import com.example.homechan.ui.devices.BlindsViewModel
import com.example.homechan.ui.devices.DevicesViewModel
import com.example.homechan.ui.devices.LampViewModel
import com.example.homechan.ui.devices.SpeakerViewModel


@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as ApiApplication)

    val deviceRepository = application.deviceRepository
    return ViewModelFactory(

        deviceRepository,
        LocalSavedStateRegistryOwner.current,
        defaultArgs
    )
}

class ViewModelFactory (

    private val deviceRepository: DeviceRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {


            isAssignableFrom(DevicesViewModel::class.java) ->
                DevicesViewModel(deviceRepository)

            isAssignableFrom(LampViewModel::class.java) ->
                LampViewModel(deviceRepository)

            isAssignableFrom(SpeakerViewModel::class.java) ->
                SpeakerViewModel(deviceRepository)

            isAssignableFrom(AcViewModel::class.java) ->
                AcViewModel(deviceRepository)

            isAssignableFrom(BlindsViewModel::class.java) ->
                BlindsViewModel(deviceRepository)

            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
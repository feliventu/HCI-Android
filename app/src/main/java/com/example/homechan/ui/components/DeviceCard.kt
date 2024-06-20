package com.example.homechan.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.homechan.R
import com.example.homechan.data.model.Ac
import com.example.homechan.data.model.Blinds
import com.example.homechan.data.model.Device
import com.example.homechan.data.model.DeviceType
import com.example.homechan.data.model.Lamp
import com.example.homechan.data.model.Speaker
import com.example.homechan.data.model.Status
import com.example.homechan.ui.devices.AcViewModel
import com.example.homechan.ui.devices.BlindsViewModel
import com.example.homechan.ui.devices.LampViewModel
import com.example.homechan.ui.devices.SpeakerViewModel
import com.example.homechan.ui.getViewModelFactory
import com.example.homechan.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch
import com.example.homechan.data.remote.model.RemoteStatus


@Composable
fun DeviceCard(
    device: Device,
    name: String = "Device",

    snackbarHostState: SnackbarHostState = SnackbarHostState(),

    ) {
    val lampViewModel: LampViewModel = viewModel(factory = getViewModelFactory())
    val speakerViewModel: SpeakerViewModel = viewModel(factory = getViewModelFactory())
    val acViewModel: AcViewModel = viewModel(factory = getViewModelFactory())
    val blindsViewModel: BlindsViewModel = viewModel(factory = getViewModelFactory())


    val scope = rememberCoroutineScope()
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val isCompact =
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT


    val uiLampState by lampViewModel.uiState.collectAsState()
    val uiSpeakerState by speakerViewModel.uiState.collectAsState()
    val uiAcState by acViewModel.uiState.collectAsState()
    val uiBlindsState by blindsViewModel.uiState.collectAsState()

    var showDialog = rememberSaveable {
        mutableStateOf(false)
    }

    var status: String = ""
    var icon: ImageVector = ImageVector.vectorResource(R.drawable.ic_devices)
    var deviceAux: Device


    if (device.type == DeviceType.LAMP) {
        icon = ImageVector.vectorResource(R.drawable.ic_lamp)
        deviceAux = device as Lamp
        uiLampState.currentDevice = deviceAux
        status = uiLampState.currentDevice?.status.toString()

    }

    if (device.type == DeviceType.AC) {
        icon = ImageVector.vectorResource(R.drawable.ic_ac)
        deviceAux = device as Ac
        uiAcState.currentDevice = deviceAux
        status = uiAcState.currentDevice?.status.toString()

        if(uiAcState.currentDevice?.status.toString() == "ON")
            status = stringResource(id = R.string.on)
        else if (uiAcState.currentDevice?.status.toString() == "OFF")
            status = stringResource(id = R.string.off)


        if (showDialog.value) {
            AcDialog(
                onDismissRequest = { showDialog.value = false },
                device = deviceAux,
                snackbarHostState
            )
        }
    }

    if (device.type == DeviceType.BLINDS) {
        icon = ImageVector.vectorResource(id = R.drawable.ic_blinds)
        deviceAux = device as Blinds
        uiBlindsState.currentDevice = deviceAux
        status = uiBlindsState.currentDevice?.status.toString()

        if(uiBlindsState.currentDevice?.status.toString() == "OPENED")
            status = stringResource(id = R.string.closed)
        else if(uiBlindsState.currentDevice?.status.toString() == "CLOSED")
            status = stringResource(id = R.string.opened)
        else {
            if (uiBlindsState.currentDevice?.status.toString() == "OPENING")
                status = stringResource(id = R.string.closing)
            else if (uiBlindsState.currentDevice?.status.toString() == "CLOSING")
                status = stringResource(id = R.string.opening)
        }

        if(showDialog.value){
            BlindsDialog(
                onDismissRequest = { showDialog.value = false },
                device = deviceAux,
                snackbarHostState
            )

        }
    }


    if (device.type == DeviceType.SPEAKER) {

        icon = ImageVector.vectorResource(R.drawable.ic_speaker)
        deviceAux = device as Speaker
        uiSpeakerState.currentDevice = deviceAux
        var genre = when (uiSpeakerState.currentDevice!!.genre) {
            "rock" -> stringResource(id = R.string.rock)
            "pop" -> stringResource(id = R.string.pop)
            "country" -> stringResource(id = R.string.country)
            "classical" -> stringResource(id = R.string.classical)
            "dance" -> stringResource(id = R.string.dance)
            "latina" -> stringResource(id = R.string.latina)
            else -> stringResource(id = R.string.no_data)

        }

        status = stringResource(id = R.string.genre) + ": " + genre

        if (showDialog.value) {
            SpeakerDialog(
                onDismissRequest = { showDialog.value = false },
                device = deviceAux,
                snackbarHostState
            )
        }

    }



    MyApplicationTheme(dynamicColor = false) {

        Box(modifier = Modifier.fillMaxWidth()) {

            Card(
                onClick = {
                    showDialog.value = true;
                },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                modifier = if (isCompact) {
                    Modifier
                        .fillMaxWidth() // Fill the maximum width available
                        .height(100.dp)
                }// Keep the height as 100.dp
                else {
                    Modifier
                        .fillMaxWidth() // Fill the maximum width available
                        .height(100.dp)
                }
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp),
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "",
                            modifier = Modifier.size(28.dp),
                        )
                        Text(
                            text = name,
                            modifier = Modifier.padding(start = 12.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                        )


                    }

                    Text(
                        text = status, fontSize = 14.sp, modifier = Modifier.padding(
                            start = 56.dp,
                            top = 2.dp
                        ),
                        color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Light
                    )


                }


                val snackbarLabel = stringResource(R.string.device_on)
                var switchState = remember { mutableStateOf(false) }

                if (device.type == DeviceType.SPEAKER) {
                    uiSpeakerState.currentDevice = device as Speaker
                    switchState =
                        remember { mutableStateOf(uiSpeakerState.currentDevice?.status != Status.STOPPED) }

                    LaunchedEffect(uiSpeakerState.currentDevice?.status) {
                        uiSpeakerState.currentDevice = device as Speaker
                        switchState.value = uiSpeakerState.currentDevice?.status != Status.STOPPED
                    }
                }

                if (device.type == DeviceType.AC) {
                    uiAcState.currentDevice = device as Ac
                    switchState =
                        remember { mutableStateOf(uiAcState.currentDevice?.status != Status.OFF) }

                    LaunchedEffect(uiAcState.currentDevice?.status) {
                        uiAcState.currentDevice = device as Ac
                        switchState.value = uiAcState.currentDevice?.status != Status.OFF
                    }
                }

                if (device.type == DeviceType.LAMP) {
                    uiLampState.currentDevice = device as Lamp
                    switchState =
                        remember { mutableStateOf(uiLampState.currentDevice?.status != Status.OFF) }

                    LaunchedEffect(uiLampState.currentDevice?.status) {
                        uiLampState.currentDevice = device as Lamp
                        switchState.value = uiLampState.currentDevice?.status != Status.OFF
                    }


                }

                if (device.type != DeviceType.BLINDS) {
                    Switch(
                        checked = switchState.value,
                        onCheckedChange = { isChecked ->
                            switchState.value = isChecked
                            if (isChecked) {
                                if (device.type == DeviceType.SPEAKER) {
                                    uiSpeakerState.currentDevice = device as Speaker
                                    speakerViewModel.play()
                                }

                                if (device.type == DeviceType.LAMP) {
                                    uiLampState.currentDevice = device as Lamp
                                    lampViewModel.turnOn()
                                }

                                if (device.type == DeviceType.AC) {
                                    uiAcState.currentDevice = device as Ac
                                    acViewModel.turnOn()
                                }

                            } else {

                                if (device.type == DeviceType.SPEAKER) {
                                    uiSpeakerState.currentDevice = device as Speaker
                                    speakerViewModel.stop()
                                }

                                if (device.type == DeviceType.LAMP) {
                                    uiLampState.currentDevice = device as Lamp
                                    lampViewModel.turnOff()
                                }

                                if (device.type == DeviceType.AC) {
                                    uiAcState.currentDevice = device as Ac
                                    acViewModel.turnOff()
                                }

                            }

                            if (switchState.value) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        snackbarLabel,
                                        withDismissAction = true
                                    )
                                }
                            }


                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = MaterialTheme.colorScheme.secondary,
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color.LightGray,
                            uncheckedBorderColor = Color.LightGray
                        ),
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(bottom = 30.dp, end = 20.dp)
                    )
                }

            }

            if (device.type == DeviceType.BLINDS) {
                deviceAux = device as Blinds
                uiBlindsState.currentDevice = deviceAux as Blinds
                status = uiBlindsState.currentDevice?.status.toString()

                var label = ""
                var disableButton = false
                if (uiBlindsState.currentDevice!!.status.toString() == "OPENED")
                    label = stringResource(id = R.string.open)
                else if (uiBlindsState.currentDevice!!.status.toString() == "CLOSED")
                    label = stringResource(id = R.string.close)
                else {
                    if (uiBlindsState.currentDevice!!.status.toString() == "OPENING")
                        label = stringResource(id = R.string.closing)
                    else if (uiBlindsState.currentDevice!!.status.toString() == "CLOSING")
                        label = stringResource(id = R.string.opening)

                    disableButton = true
                }

                Box(
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    var snackbarLabel = stringResource(id = R.string.blinds_update)
                    CustomOutlinedButton(label = label, enabled = !disableButton, onClick = {
                        if (uiBlindsState.currentDevice!!.status.toString() == "OPENED"){
                            deviceAux = device as Blinds
                            uiBlindsState.currentDevice = deviceAux as Blinds
                            blindsViewModel.close()
                        }

                        else if (uiBlindsState.currentDevice!!.status.toString() == "CLOSED"){
                            deviceAux = device as Blinds
                            uiBlindsState.currentDevice = deviceAux as Blinds
                            blindsViewModel.open()

                        }
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                snackbarLabel,
                                withDismissAction = true
                            )
                        }

                    },
                        modifier = Modifier
                            .padding(bottom =10.dp, end = 15.dp))
                }
            }

        }


    }
}
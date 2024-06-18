package com.example.homechan.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.homechan.R
import com.example.homechan.data.model.Device
import com.example.homechan.data.model.Lamp
import com.example.homechan.data.model.Status
import com.example.homechan.ui.devices.LampViewModel
import com.example.homechan.ui.getViewModelFactory
import com.example.homechan.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch


@Composable
fun DeviceCard(
    device : Device,
    name: String = "Device",
    icon: ImageVector = ImageVector.vectorResource(R.drawable.ic_devices),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    lampViewModel: LampViewModel = viewModel(factory = getViewModelFactory())
) {
    val scope = rememberCoroutineScope()
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val isCompact = adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
    val uiLampState by lampViewModel.uiState.collectAsState()


    val lamp = device as Lamp


    uiLampState.currentDevice = lamp
    val status = uiLampState.currentDevice?.status.toString()
    MyApplicationTheme(dynamicColor = false) {
        Card(
            onClick = {

            },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            modifier = if(isCompact){
                Modifier
                    .fillMaxWidth() // Fill the maximum width available
                    .height(100.dp)
            }// Keep the height as 100.dp
            else {
                Modifier
                    .width(400.dp) // Fill the maximum width available
                    .height(100.dp)
            }
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",

                        )
                    Text(
                        text = name,
                        modifier = Modifier.padding(start = 16.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                    )
                }

                Text(
                    text = status, fontSize = 14.sp, modifier = Modifier.padding(start = 56.dp),
                    color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Light
                )
            }

            val snackbarLabel= stringResource(R.string.device_on)
            var checked by rememberSaveable { mutableStateOf(uiLampState.currentDevice?.status != Status.OFF) }

            Switch(
                checked = checked,
                onCheckedChange = {
                    uiLampState.currentDevice = lamp
                    checked = it
                    if (checked) {
                    scope.launch {
                        snackbarHostState.showSnackbar( snackbarLabel, withDismissAction = true)
                    }}
                    if (checked) {
                        lampViewModel.turnOn()
                    } else {
                        lampViewModel.turnOff()
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
}
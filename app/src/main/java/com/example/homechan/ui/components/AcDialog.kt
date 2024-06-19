package com.example.homechan.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homechan.R
import com.example.homechan.data.model.Ac
import com.example.homechan.data.model.Device
import com.example.homechan.data.model.DeviceType
import com.example.homechan.data.model.Speaker
import com.example.homechan.data.model.Status
import com.example.homechan.ui.devices.AcViewModel
import com.example.homechan.ui.getViewModelFactory

enum class AcDialogState {
    MAIN_DIALOG,
    TEMPERATURE_DIALOG,
    MODE_DIALOG,
    VERTICAL_SWING_DIALOG,
    HORIZONTAL_SWING_DIALOG,
    FAN_SPEED_DIALOG,

}



@Composable
fun AcDialog(
    onDismissRequest: () -> Unit,
    device: Device,
){
    val dialogState = rememberSaveable { mutableStateOf(AcDialogState.MAIN_DIALOG) }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary,
                contentColor = MaterialTheme.colorScheme.primary
            ),
        ) {
            when (dialogState.value) {
                    AcDialogState.MAIN_DIALOG -> MainDialog(dialogState, device)
                    AcDialogState.TEMPERATURE_DIALOG -> TemepratureDialog(dialogState, device)
                    AcDialogState.MODE_DIALOG -> ModeDialog(dialogState, device)
                    AcDialogState.VERTICAL_SWING_DIALOG -> VerticalSwingDialog(dialogState, device)
                    AcDialogState.HORIZONTAL_SWING_DIALOG -> HorizontalSwingDialog(dialogState, device)
                    AcDialogState.FAN_SPEED_DIALOG -> FanSpeedDialog(dialogState, device)
            }
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
internal fun MainDialog(
    dialogState: MutableState<AcDialogState>,
    device: Device
) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        val acViewModel: AcViewModel = viewModel(factory = getViewModelFactory())
        val uiAcState by acViewModel.uiState.collectAsState()
        val ac = device as Ac
        uiAcState.currentDevice = ac

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()

        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_devices),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            );
            Text(
                text = "Ac",
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))


            var switchState by remember { mutableStateOf(uiAcState.currentDevice?.status != Status.OFF) }
            LaunchedEffect(uiAcState.currentDevice?.status) {
                switchState = uiAcState.currentDevice?.status != Status.OFF
            }
            Switch(
                checked = switchState,
                onCheckedChange = { isChecked ->
                    switchState = isChecked
                    if (isChecked) {
                            uiAcState.currentDevice = device as Ac
                            acViewModel.turnOn()
                    } else {
                            uiAcState.currentDevice = device as Ac
                            acViewModel.turnOff()

                    }
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = MaterialTheme.colorScheme.secondary,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.LightGray,
                    uncheckedBorderColor = Color.LightGray
                ),
            )
        }
        Row(
            modifier = Modifier.padding(top = 16.dp),
        ){
            Text(text = stringResource(id = R.string.temperature))
            Spacer(modifier = Modifier.weight(1f))
            Text(text =  uiAcState.currentDevice!!.temperature.toString(),
                modifier = Modifier.padding(end = 10.dp),
                color = MaterialTheme.colorScheme.tertiary)
            CustomButtonArrowMini { dialogState.value = AcDialogState.TEMPERATURE_DIALOG}
        }
        Row(
            modifier = Modifier.padding(top = 15.dp),
        ){
            Text(text = stringResource(id = R.string.mode))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = uiAcState.currentDevice!!.mode,
                modifier = Modifier.padding(end = 10.dp),
                color = MaterialTheme.colorScheme.tertiary)
            CustomButtonArrowMini { dialogState.value = AcDialogState.MODE_DIALOG}
        }
        Row(
            modifier = Modifier.padding(top = 15.dp),
        ){
            Text(text = stringResource(id = R.string.verticalswing))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = uiAcState.currentDevice!!.verticalSwing,
                modifier = Modifier.padding(end = 10.dp),
                color = MaterialTheme.colorScheme.tertiary)
            CustomButtonArrowMini { dialogState.value = AcDialogState.VERTICAL_SWING_DIALOG}
        }
        Row(
            modifier = Modifier.padding(top = 15.dp),
        ){
            Text(text = stringResource(id = R.string.horizontalswing))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = uiAcState.currentDevice!!.horizontalSwing,
                modifier = Modifier.padding(end = 10.dp),
                color = MaterialTheme.colorScheme.tertiary)
            CustomButtonArrowMini { dialogState.value = AcDialogState.HORIZONTAL_SWING_DIALOG}
        }
        Row(
            modifier = Modifier.padding(top = 15.dp),
        ){
            Text(text = stringResource(id = R.string.fanspeed))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = uiAcState.currentDevice!!.fanSpeed,
                modifier = Modifier.padding(end = 10.dp),
                color = MaterialTheme.colorScheme.tertiary)
            CustomButtonArrowMini { dialogState.value = AcDialogState.FAN_SPEED_DIALOG}
        }

    }
}

@Composable
fun TemepratureDialog(
    dialogState:  MutableState<AcDialogState>,
    device: Device
){
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        val acViewModel: AcViewModel = viewModel(factory = getViewModelFactory())
        val uiAcState by acViewModel.uiState.collectAsState()
        val ac = device as Ac
        uiAcState.currentDevice = ac

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()

        ) {
            CustomButtonArrowMedium { dialogState.value = AcDialogState.MAIN_DIALOG }
            Text(
                text = stringResource(id = R.string.back),
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 24.dp, bottom = 15.dp)
                .fillMaxWidth(), // Fill the entire width
            horizontalArrangement = Arrangement.SpaceEvenly, // Space items evenly
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.padding(start = 50.dp)) {
                CustomOutlinedButtonIcon(
                    onClick = { acViewModel.setTemperature(uiAcState.currentDevice!!.temperature+ 1 ) },
                    icon = ImageVector.vectorResource(R.drawable.ic_up_arrow),
                    enabled = uiAcState.currentDevice!!.temperature < 34
                    )
            }
            Box(modifier = Modifier.padding(end = 50.dp)) {
                CustomOutlinedButtonIcon(
                    onClick = { acViewModel.setTemperature(uiAcState.currentDevice!!.temperature - 1) },
                    icon = ImageVector.vectorResource(R.drawable.ic_down_arrow),
                    enabled = uiAcState.currentDevice!!.temperature >= 17
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp),

            ) {
            Text(text = stringResource(id = R.string.temperature))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = uiAcState.currentDevice!!.temperature.toString(), modifier = Modifier.padding(end = 10.dp), color = MaterialTheme.colorScheme.tertiary)
        }
    }
}

@Composable
fun ModeDialog(
    dialogState:  MutableState<AcDialogState>,
    device: Device
) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        val acViewModel: AcViewModel = viewModel(factory = getViewModelFactory())
        val uiAcState by acViewModel.uiState.collectAsState()
        val ac = device as Ac
        uiAcState.currentDevice = ac

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()

        ) {
            CustomButtonArrowMedium { dialogState.value = AcDialogState.MAIN_DIALOG }
            Text(
                text = stringResource(id = R.string.back),
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 24.dp, bottom = 15.dp)
                .fillMaxWidth(), // Fill the entire width
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.padding(start = 25.dp)
            ) {
                CustomOutlinedButtonIcon(
                    onClick = { acViewModel.setMode("cool")
                    },
                    icon = ImageVector.vectorResource(R.drawable.ic_ac_cool),
                    enabled = uiAcState.currentDevice!!.mode != "cool"
                    )
            }
            Box() {
                CustomOutlinedButtonIcon(
                    onClick = { acViewModel.setMode("heat") },
                    icon = ImageVector.vectorResource(R.drawable.ic_ac_heat),
                    enabled = uiAcState.currentDevice!!.mode != "heat"
                )
            }
            Box(modifier = Modifier.padding(end = 25.dp)) {
                CustomOutlinedButtonIcon(
                    onClick = { acViewModel.setMode("fan") },
                    icon = ImageVector.vectorResource(R.drawable.ic_ac_fan),
                    enabled = uiAcState.currentDevice!!.mode != "fan"
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp),

            ) {
            Text(text = stringResource(id = R.string.mode))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = ac.mode, modifier = Modifier.padding(end = 10.dp), color = MaterialTheme.colorScheme.tertiary)
        }
    }
}


@Composable
fun VerticalSwingDialog(
    dialogState:  MutableState<AcDialogState>,
    device: Device
){
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()

        ) {
            CustomButtonArrowMedium { dialogState.value = AcDialogState.MAIN_DIALOG }
            Text(
                text = stringResource(id = R.string.back),
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}


@Composable
fun HorizontalSwingDialog(
    dialogState:  MutableState<AcDialogState>,
    device: Device
){
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()

        ) {
            CustomButtonArrowMedium { dialogState.value = AcDialogState.MAIN_DIALOG }
            Text(
                text = stringResource(id = R.string.back),
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}


@Composable
fun FanSpeedDialog(
    dialogState:  MutableState<AcDialogState>,
    device: Device){
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()

        ) {
            CustomButtonArrowMedium { dialogState.value = AcDialogState.MAIN_DIALOG }
            Text(
                text = stringResource(id = R.string.back),
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
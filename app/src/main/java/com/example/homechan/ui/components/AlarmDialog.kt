package com.example.homechan.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homechan.R
import com.example.homechan.data.model.Alarm
import com.example.homechan.data.model.Device
import com.example.homechan.data.remote.model.RemoteStatus
import com.example.homechan.ui.devices.AlarmViewModel
import com.example.homechan.ui.getViewModelFactory
import kotlinx.coroutines.launch


enum class AlarmDialogState {
    PASS_DIALOG,
    MAIN_DIALOG,

}

@Composable
fun AlarmDialog(
    onDismissRequest: () -> Unit = {},
    device: Device,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    ) {

    val dialogState = rememberSaveable { mutableStateOf(AlarmDialogState.PASS_DIALOG) }
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
                AlarmDialogState.MAIN_DIALOG -> MainDialog(dialogState, device, snackbarHostState)
                AlarmDialogState.PASS_DIALOG -> PassDialog(dialogState, device)

            }
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
internal fun PassDialog(
    dialogState: MutableState<AlarmDialogState>,
    device: Device
) {
    // Main dialog content
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        val alarmViewModel: AlarmViewModel = viewModel(factory = getViewModelFactory())
        val uiAlarmState by alarmViewModel.uiState.collectAsState()
        var deviceAux = device as Alarm
        uiAlarmState.currentDevice = deviceAux
        var password: Int = 0


        Row(
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()

        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_alarm),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            );
            Text(
                text = "Alarm",
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            CustomTextField(
                label = stringResource(id = R.string.password), PasswordVisualTransformation(),
                KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                onTextChange = { text ->
                    password = text.toInt()
                },
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            CustomOutlinedButton(
                label = stringResource(id = R.string.enter),
                onClick = {
                    if( password == 1234){
                        dialogState.value = AlarmDialogState.MAIN_DIALOG
                    }
                    else{
                        //snackbar error password
                        password = 0;
                }

                },
            )
        }

    }
}

@Composable
internal fun MainDialog(
    dialogState: MutableState<AlarmDialogState>,
    device: Device,
    snackbarHostState: SnackbarHostState = SnackbarHostState()
) {
    val scope = rememberCoroutineScope()
    var snackbarLabel = stringResource(id = R.string.alarm_away_update)
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        val alarmViewModel: AlarmViewModel = viewModel(factory = getViewModelFactory())
        val uiAlarmState by alarmViewModel.uiState.collectAsState()
        var deviceAux = device as Alarm
        uiAlarmState.currentDevice = deviceAux

        var status = ""

        if(uiAlarmState.currentDevice?.status.toString() == "DISARMED")
            status = stringResource(id =R.string.disarmed)
        else if(uiAlarmState.currentDevice?.status.toString() == "ARMED_AWAY")
            status = stringResource(id =R.string.armed_away)
        else if(uiAlarmState.currentDevice?.status.toString() == "ARMED_STAY")
            status = stringResource(id =R.string.armed_stay)


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()

        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_alarm),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            );
            Text(
                text = "Alarm",
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
        }


        Row(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 10.dp)
                .fillMaxWidth(), // Fill the entire width
            horizontalArrangement = Arrangement.SpaceEvenly, // Space items evenly
            verticalAlignment = Alignment.CenterVertically
        ) {

            CustomOutlinedButton(
                label = stringResource(id = R.string.armaway),
                onClick = { alarmViewModel.armAway()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            snackbarLabel,
                            withDismissAction = true
                        )
                    }},
                enabled = uiAlarmState.currentDevice?.status.toString() != "ARMED_AWAY"
            )
            CustomOutlinedButton(
                label = stringResource(id = R.string.armstay),
                onClick = { alarmViewModel.armStay()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            snackbarLabel,
                            withDismissAction = true
                        )}},
                enabled = uiAlarmState.currentDevice?.status.toString() != "ARMED_STAY"
            )


        }
        Row(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .fillMaxWidth(), // Fill the entire width
            horizontalArrangement = Arrangement.SpaceEvenly, // Space items evenly
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomOutlinedButton(
                label = stringResource(id = R.string.disarm),
                onClick = { alarmViewModel.disarm()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            snackbarLabel,
                            withDismissAction = true
                        )}},
                enabled = uiAlarmState.currentDevice?.status.toString() != "DISARMED"
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 10.dp),
        ) {
            Text(text = stringResource(id = R.string.status))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = status,
                modifier = Modifier.padding(end = 10.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
        }

    }
}






package com.example.homechan.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import com.example.homechan.data.model.Alarm
import com.example.homechan.data.model.Device
import com.example.homechan.data.remote.model.RemoteStatus
import com.example.homechan.ui.devices.AlarmViewModel
import com.example.homechan.ui.getViewModelFactory
import com.example.homechan.ui.theme.MyApplicationTheme


@Composable
fun AlarmCard(
    device: Device,
    name: String = "Alarm"
) {
    val alarmViewModel: AlarmViewModel = viewModel(factory = getViewModelFactory())
    val uiAlarmState by alarmViewModel.uiState.collectAsState()
    var deviceAux = device as Alarm
    uiAlarmState.currentDevice = deviceAux
    var status = RemoteStatus.ARMED_AWAY
    if(uiAlarmState.currentDevice?.status.toString() == "DISARMED")
        status = stringResource(id =R.string.disarmed)
    else if(uiAlarmState.currentDevice?.status.toString() == "ARMED_AWAY")
        status = stringResource(id =R.string.armed_away)
    else if(uiAlarmState.currentDevice?.status.toString() == "ARMED_STAY")
        status = stringResource(id =R.string.armed_stay)



    val adaptiveInfo = currentWindowAdaptiveInfo()
    val isCompact =
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlarmDialog(onDismissRequest = { showDialog = false },
            device = deviceAux)
    }

    MyApplicationTheme(dynamicColor = false) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            onClick = { showDialog = true },
            modifier = if (isCompact) {
                Modifier
                    .fillMaxWidth() // Fill the maximum width available
                    .height(85.dp)
            }// Keep the height as 100.dp
            else {
                Modifier
                    .width(400.dp) // Fill the maximum width available
                    .height(85.dp)
            }
        ) {
            Column {
                Row(


                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_alarm),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .size(28.dp),

                        )
                    Text(
                        text = name,
                        modifier = Modifier.padding(start = 13.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                    )
                }

                Text(
                    text = status, fontSize = 14.sp, modifier = Modifier.padding(start = 58.dp),
                    color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Light
                )
            }


        }


    }
}
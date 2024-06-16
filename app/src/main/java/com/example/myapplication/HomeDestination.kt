package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.components.AlarmCard
import com.example.myapplication.components.AlarmDialog
import com.example.myapplication.components.DeviceCard
import com.example.myapplication.components.CustomDropdown
import com.example.myapplication.components.RoutineCard
import com.example.myapplication.components.SpeakerDialog
import com.example.myapplication.ui.theme.YellowR

@Composable
fun MyHomeDestination() {
    Column(
        modifier = Modifier
            .padding(start = 18.dp, end = 18.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp),

        ) {


        Text(
            text = stringResource(id = R.string.alarms),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 15.dp)
        )
        AlarmCard()




        Text(
            text = stringResource(id = R.string.recent_routines),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,

            )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            items(5) {
                RoutineCard(false, "Routine ", "Descripción de la rutina 1", color = YellowR)
            }
        }



        Text(
            text = stringResource(id = R.string.recent_devices),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        DeviceCard()
        DeviceCard()

        var showDialog by remember { mutableStateOf(false) }
        Button(onClick = { showDialog = true }) {
            Text("Show Dialog")
        }

        if (showDialog) {
            SpeakerDialog(onDismissRequest = { showDialog = false })
        }

        var showDialog2 by remember { mutableStateOf(false) }
        Button(onClick = { showDialog2 = true }) {
            Text("Show Dialog alarm")
        }

        if (showDialog2) {
            AlarmDialog(onDismissRequest = { showDialog2 = false })
        }
    }
}
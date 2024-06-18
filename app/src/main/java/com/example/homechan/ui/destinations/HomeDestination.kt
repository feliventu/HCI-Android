package com.example.homechan.ui.destinations


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homechan.R
import com.example.homechan.ui.components.AcDialog
import com.example.homechan.ui.components.AlarmCard
import com.example.homechan.ui.components.BlindsDialog
import com.example.homechan.ui.components.DeviceCard
import com.example.homechan.ui.components.RoutineCard
import com.example.homechan.ui.components.SpeakerDialog
import com.example.homechan.ui.theme.YellowR

@Composable
fun MyHomeDestination(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
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


        var showDialog by remember { mutableStateOf(false) }
        Button(onClick = { showDialog = true

        }) {
            Text("Speaker dialog")
        }

        if (showDialog) {
            SpeakerDialog(onDismissRequest = { showDialog = false }, snackbarHostState = snackbarHostState)
        }

        var showDialog1 by remember { mutableStateOf(false) }
        Button(onClick = { showDialog1 = true }) {
            Text("AC dialog")
        }

        if (showDialog1) {
            AcDialog(onDismissRequest = { showDialog1 = false })
        }

        var showDialog2 by remember { mutableStateOf(false) }
        Button(onClick = { showDialog2 = true
        }) {
            Text("Blinds dialog")
        }

        if (showDialog2) {
            BlindsDialog(onDismissRequest = { showDialog2 = false })
        }



        DeviceCard( snackbarHostState = snackbarHostState)
        DeviceCard( snackbarHostState = snackbarHostState)
        
    }
}
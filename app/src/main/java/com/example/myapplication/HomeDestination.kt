package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.components.AlarmCard
import com.example.myapplication.components.CustomTextField
import com.example.myapplication.components.DeviceCard
import com.example.myapplication.components.DropdownMenuBox
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
        DropdownMenuBox( arrayOf("Casa 1", "Casa 2", "Casa 3"))

        Text(
            text = stringResource(id = R.string.alarms),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 0.dp)
        )
        AlarmCard()




        Text(
            text = stringResource(id = R.string.recent_routines),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,

            )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            items(5) {
                RoutineCard(false, "Rutina 1334", "Descripción de la rutina 1", color = YellowR)
            }
        }



        Text(
            text = stringResource(id = R.string.recent_devices),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    
        SpeakerDialog(onDismissRequest = { })
        DeviceCard()
        DeviceCard()
        
    }
}
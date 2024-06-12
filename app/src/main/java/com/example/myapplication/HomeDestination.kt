package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.components.AlarmCard
import com.example.myapplication.components.DeviceCard
import com.example.myapplication.components.RoutineCard
import com.example.myapplication.ui.theme.YellowR

@Composable
fun MyHomeDestination() {
    Column(
        modifier = Modifier
            .padding(start = 18.dp, end = 18.dp,)
            .verticalScroll(rememberScrollState())
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp),

    ) {
        Text(
            text = "Alarmas",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 18.dp)
        )
        AlarmCard()

        Text(
            text = "Rutinas recientes",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,

        )
        LazyRow (horizontalArrangement = Arrangement.spacedBy(15.dp)){
            items(5) {
                RoutineCard(false, "Rutina 1234", "Descripción de la rutina 1", color = YellowR)



            }
        }



        Text(
            text = "Dispositivos recientes",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )


        DeviceCard()
        DeviceCard()
        DeviceCard()
        DeviceCard()
        DeviceCard()
        DeviceCard()


    }


}
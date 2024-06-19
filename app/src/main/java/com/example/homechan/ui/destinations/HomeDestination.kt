package com.example.homechan.ui.destinations


import android.content.Context
import android.os.Build
import android.view.Surface
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.Posture
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowWidthSizeClass

import com.example.homechan.R
import com.example.homechan.ui.components.AcDialog
import com.example.homechan.ui.components.AlarmCard
import com.example.homechan.ui.components.BlindsDialog
import com.example.homechan.ui.components.DeviceCard
import com.example.homechan.ui.components.RoutineCard
import com.example.homechan.ui.components.SpeakerDialog
import com.example.homechan.ui.devices.DevicesViewModel
import com.example.homechan.ui.devices.LampViewModel
import com.example.homechan.ui.getViewModelFactory
import com.example.homechan.ui.theme.YellowR
import com.google.android.gms.location.DeviceOrientation

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MyHomeDestination(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    viewModel: DevicesViewModel = viewModel(factory = getViewModelFactory()),
) {
    val uiState by viewModel.uiState.collectAsState()

    val adaptiveInfo = currentWindowAdaptiveInfo()
    val isCompact =
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT


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


//            for (device in uiState.devices) {
//
//                DeviceCard( device = device, name = device.name, snackbarHostState = snackbarHostState)
//            }


        var columsize = 1
        if (isCompact && isLandscape(LocalContext.current) ||
            !isCompact && isLandscape(LocalContext.current)
        ) {
            columsize = 2
        } else if (!isCompact && !isLandscape(LocalContext.current)) {
            columsize = 3
        }




        for (i in 0 until uiState.devices.size step columsize) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.padding(bottom = 5.dp)
            ) {
                for (j in i until minOf(i + columsize, uiState.devices.size)) {

                    Box(
                        modifier = Modifier.fillMaxWidth().weight(1f)
                    ) {
                    DeviceCard(
                        device = uiState.devices[j],
                        name = uiState.devices[j].name,
                        snackbarHostState = snackbarHostState,

                    )}
                }
            }
        }


//        DeviceCard( snackbarHostState = snackbarHostState)


    }
}

@RequiresApi(Build.VERSION_CODES.R)
fun isLandscape(context: Context): Boolean {
    val orientation = context.display?.rotation ?: 0

    return orientation == Surface.ROTATION_90 || orientation == Surface.ROTATION_270
}

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.Posture
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowWidthSizeClass

import com.example.homechan.R
import com.example.homechan.data.model.DeviceType
import com.example.homechan.ui.components.AcDialog
import com.example.homechan.ui.components.AlarmCard
import com.example.homechan.ui.components.BlindsDialog
import com.example.homechan.ui.components.DeviceCard
import com.example.homechan.ui.components.RoutineCard
import com.example.homechan.ui.components.ShimmerListItem
import com.example.homechan.ui.components.SpeakerDialog
import com.example.homechan.ui.devices.DevicesViewModel
import com.example.homechan.ui.devices.LampViewModel
import com.example.homechan.ui.getViewModelFactory
import com.example.homechan.ui.theme.YellowR
import com.google.android.gms.location.DeviceOrientation
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MyHomeDestination(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    viewModel: DevicesViewModel = viewModel(factory = getViewModelFactory()),
) {
    Loading()
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
        if(uiState.devices.isEmpty()){
            Column(
                modifier = Modifier
                    .padding(top = 56.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.welcome),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(top = 15.dp),
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp
                )
            }
        }


        if (uiState.devices.any { it.type == DeviceType.ALARM }) {
            Text(
                text = stringResource(id = R.string.alarms),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 15.dp)
            )

            for (device in uiState.devices) {
                if (device.type == DeviceType.ALARM) {
                    AlarmCard(device = device, name = device.name)
                }
            }
        }




        if(uiState.devices.any { it.type == DeviceType.LAMP || it.type == DeviceType.BLINDS || it.type == DeviceType.SPEAKER }) {

            Text(
                text = stringResource(id = R.string.recent_devices),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

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
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            if (uiState.devices[j].type != DeviceType.ALARM)
                                DeviceCard(
                                    device = uiState.devices[j],
                                    name = uiState.devices[j].name,
                                    snackbarHostState = snackbarHostState,

                                    )
                        }
                    }
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

@Composable
fun Loading() {
    var isLoading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true) {
        delay(3000)
        isLoading = false
    }
    if (isLoading) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(1) {
                ShimmerListItem(
                    isLoading = isLoading,
                    contentAfterLoading = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp)
                        ) {


                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp, start = 18.dp, end = 18.dp)
                )
            }
        }
    }


}
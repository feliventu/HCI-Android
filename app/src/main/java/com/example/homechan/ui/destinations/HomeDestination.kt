package com.example.homechan.ui.destinations


import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.Surface
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowWidthSizeClass

import com.example.homechan.R
import com.example.homechan.data.model.DeviceType
import com.example.homechan.ui.components.AlarmCard
import com.example.homechan.ui.components.DeviceCard
import com.example.homechan.ui.components.ShimmerListItem
import com.example.homechan.ui.devices.DevicesViewModel
import com.example.homechan.ui.getViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
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

        var connectionError = !uiState.isFetching && uiState.error != null

        if (connectionError) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(id = R.string.connection_error),
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(top = 45.dp),
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 30.sp,
                        fontWeight = FontWeight.SemiBold,

                    )
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_error),
                        contentDescription = "error", tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(80.dp).padding(top = 15.dp))
                    Text(
                        text = "Error: ${uiState.error!!.message}",
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(top = 15.dp),
                        fontSize = 18.sp,

                        )
                    val snackbarLabel = "Error: ${uiState.error!!.message}"

                    scope.launch {
                        snackbarHostState.showSnackbar(
                            snackbarLabel,
                            withDismissAction = true
                        )
                    }
                }
            }
        }


        if (uiState.devices.isEmpty() && !connectionError) {
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
                var imageSize = if (isCompact) 300.dp else 400.dp
                Image(
                    painter = painterResource(id = R.drawable.im_welcome),
                    contentDescription = "Home",
                    modifier = Modifier.size(imageSize),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
                )
            }
        }

        val alarmDevices = uiState.devices.filter { it.type == DeviceType.ALARM }
        val nonAlarmDevices = uiState.devices.filter { it.type != DeviceType.ALARM }



        if (alarmDevices.isNotEmpty() && !connectionError) {
            val limitedAlarmDevices =
                if (alarmDevices.size >= 2) alarmDevices.take(2) else alarmDevices
            val limitedAlarmDevicesSize = if (alarmDevices.size >= 2) 2 else alarmDevices.size
            Row {
                Text(
                    text = stringResource(id = R.string.recent_alarms),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 15.dp)
                )

                Text(
                    text = "($limitedAlarmDevicesSize)",
                    modifier = Modifier.padding(start = 5.dp, top = 15.dp),
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }


            var columsize = 1
            if (isCompact && isLandscape(LocalContext.current) ||
                !isCompact && isLandscape(LocalContext.current)
            ) {
                columsize = 2
            } else if (!isCompact && !isLandscape(LocalContext.current)) {
                columsize = 3
            }




            for (i in limitedAlarmDevices.indices step columsize) {
                val isLastRow = i + columsize >= nonAlarmDevices.size
                val paddingBottom = if (isLastRow) 20.dp else 5.dp
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.padding(bottom = paddingBottom)
                ) {
                    for (j in i until minOf(i + columsize, limitedAlarmDevices.size)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            if (limitedAlarmDevices[j].type == DeviceType.ALARM)
                                AlarmCard(
                                    device = limitedAlarmDevices[j],
                                    name = limitedAlarmDevices[j].name,
                                    snackbarHostState = snackbarHostState,
                                )
                        }
                    }
                }
            }
        }



        if (nonAlarmDevices.isNotEmpty() && !connectionError) {

            var paddingTop = if (alarmDevices.isNotEmpty()) 0.dp else 15.dp
            val limitedNonAlarmDevices =
                if (nonAlarmDevices.size >= 4) nonAlarmDevices.take(4) else nonAlarmDevices
            val limitedNonAlarmDevicesSize =
                if (nonAlarmDevices.size >= 4) 4 else nonAlarmDevices.size

            Row {

                Text(
                    text = stringResource(id = R.string.recent_devices),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = paddingTop)
                )
                Text(
                    text = "($limitedNonAlarmDevicesSize)",
                    modifier = Modifier.padding(start = 5.dp, top = paddingTop),
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }

            var columsize = 1
            if (isCompact && isLandscape(LocalContext.current) ||
                !isCompact && isLandscape(LocalContext.current)
            ) {
                columsize = 2
            } else if (!isCompact && !isLandscape(LocalContext.current)) {
                columsize = 3
            }




            for (i in limitedNonAlarmDevices.indices step columsize) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.padding(bottom = 5.dp)
                ) {
                    for (j in i until minOf(i + columsize, limitedNonAlarmDevices.size)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            if (limitedNonAlarmDevices[j].type != DeviceType.ALARM)
                                DeviceCard(
                                    device = limitedNonAlarmDevices[j],
                                    name = limitedNonAlarmDevices[j].name,
                                    snackbarHostState = snackbarHostState,
                                )
                        }
                    }
                }
            }
        }


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
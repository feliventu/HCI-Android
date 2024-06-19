package com.example.homechan.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homechan.R
import com.example.homechan.data.model.Blinds
import com.example.homechan.data.model.Device
import com.example.homechan.ui.devices.BlindsViewModel
import com.example.homechan.ui.getViewModelFactory


@SuppressLint("SuspiciousIndentation")
@Composable
fun BlindsDialog(
    onDismissRequest: () -> Unit,
    device: Device,
    ) {
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
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                val blindsViewModel: BlindsViewModel = viewModel(factory = getViewModelFactory())
                val uiBlindsState by blindsViewModel.uiState.collectAsState()
                val blinds = device as Blinds
                uiBlindsState.currentDevice = blinds
                var label = ""
                var disableButton = false
                if(uiBlindsState.currentDevice!!.status.toString() == "OPENED")
                    label = stringResource(id = R.string.open)
                else if(uiBlindsState.currentDevice!!.status.toString() == "CLOSED")
                    label = stringResource(id = R.string.close)
                else {
                    if (uiBlindsState.currentDevice!!.status.toString() == "OPENING")
                        label = stringResource(id = R.string.opening)
                    else if (uiBlindsState.currentDevice!!.status.toString() == "CLOSING")
                        label = stringResource(id = R.string.closing)

                        disableButton = true
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()

                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_blinds),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    );
                    Text(
                        text = uiBlindsState.currentDevice!!.name,
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CustomOutlinedButton(onClick = {
                        if(uiBlindsState.currentDevice!!.status.toString() == "OPENED")
                            blindsViewModel.close()
                        else if(uiBlindsState.currentDevice!!.status.toString() == "CLOSED")
                            blindsViewModel.open()
                    },
                    label = label,
                    enabled = label == stringResource(id = R.string.open) || label == stringResource(id = R.string.close)
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
                            onClick = { blindsViewModel.setLevel( uiBlindsState.currentDevice!!.level + 10) },
                            icon = ImageVector.vectorResource(R.drawable.ic_up_arrow),
                            enabled = uiBlindsState.currentDevice!!.level < 100 && !disableButton
                            )
                    }
                    Box(modifier = Modifier.padding(end = 50.dp)) {
                        CustomOutlinedButtonIcon(
                            onClick = { blindsViewModel.setLevel(uiBlindsState.currentDevice!!.level - 10) },
                            icon = ImageVector.vectorResource(R.drawable.ic_down_arrow),
                            enabled = uiBlindsState.currentDevice!!.level > 0 && !disableButton
                        )
                    }
                }


                Row(
                        modifier = Modifier.padding(top = 16.dp),
                ){
                Text(text = stringResource(id = R.string.actualstatus))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = uiBlindsState.currentDevice!!.currentLevel.toString(),
                    modifier = Modifier.padding(end = 10.dp),
                    color = MaterialTheme.colorScheme.tertiary)
            }
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                ){
                    Text(text = stringResource(id = R.string.maxaperture))
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = uiBlindsState.currentDevice!!.level.toString(),
                        modifier = Modifier.padding(end = 10.dp),
                        color = MaterialTheme.colorScheme.tertiary)
                }

            }
        }
    }
}


package com.example.myapplication.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults


import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme


@Composable
fun SpeakerDialog(
    onDismissRequest: () -> Unit,
    id: String = null.toString(),
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
        ) {
            MyApplicationTheme(dynamicColor = false) {
                Column(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    Row(
                       verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth()

                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_devices),
                            contentDescription = "xd",
                            modifier = Modifier.size(30.dp)
                            );
                        Text(
                            text = "Speaker",
                            textAlign = TextAlign.Left,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))
                        var checked by rememberSaveable { mutableStateOf(true) }
                        Switch(
                            checked = checked,
                            onCheckedChange = {
                                checked = it
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = MaterialTheme.colorScheme.secondary,
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = Color.LightGray,
                                uncheckedBorderColor = Color.LightGray
                            ),
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        CustomOutlinedButtonIcon(
                            onClick = { /*TODO*/ },
                            icon = Icons.Default.Done,
                        )
                        CustomOutlinedButtonIcon(
                            onClick = { /*TODO*/ },
                            icon = Icons.Default.Done,
                        )
                        CustomOutlinedButtonIcon(
                            onClick = { /*TODO*/ },
                            icon = Icons.Default.Done,
                        )
                        CustomOutlinedButtonIcon(
                            onClick = { /*TODO*/ },
                            icon = Icons.Default.Done,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp),
                    ){
                        Text(text = "Cancion")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Taylor swift", modifier = Modifier.padding(end = 10.dp), color = Color.Gray)
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp),
                    ){
                        Text(text = "Volumen")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "1", modifier = Modifier.padding(end = 10.dp), color = Color.Gray)
                }
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp),
                    ){
                        Text(text = "Genero")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Para Follar", modifier = Modifier.padding(end = 10.dp), color = Color.Gray)

                    }

                }
            }
        }

    }
}





@Preview(showBackground = true)
@Composable
fun SpeakerDialogPreview() {
    SpeakerDialog(
        onDismissRequest = { },
    )
}


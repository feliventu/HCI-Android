package com.example.myapplication.components

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.R


@Composable
fun BlindsDialog(
    onDismissRequest: () -> Unit,
    id: String = null.toString(),
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()

                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_devices),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    );
                    Text(
                        text = "Blind",
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CustomOutlinedButton(onClick = { /*TODO*/ },
                    label = "Open",
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
                            onClick = { /*TODO*/ },
                            icon = ImageVector.vectorResource(R.drawable.ic_up_arrow),

                            )
                    }
                    Box(modifier = Modifier.padding(end = 50.dp)) {
                        CustomOutlinedButtonIcon(
                            onClick = { /*TODO*/ },
                            icon = ImageVector.vectorResource(R.drawable.ic_down_arrow),
                        )
                    }
                }


                Row(
                        modifier = Modifier.padding(top = 16.dp),
                ){
                Text(text = stringResource(id = R.string.actualstatus))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "50",
                    modifier = Modifier.padding(end = 10.dp),
                    color = MaterialTheme.colorScheme.tertiary)
            }
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                ){
                    Text(text = stringResource(id = R.string.maxaperture))
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "50",
                        modifier = Modifier.padding(end = 10.dp),
                        color = MaterialTheme.colorScheme.tertiary)
                }

            }
        }
    }
}


package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme

@Preview
@Composable
fun AlarmCard(
    name: String = "Alarm",

) {



    val adaptiveInfo = currentWindowAdaptiveInfo()
    val isCompact =
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlarmDialog(onDismissRequest = { showDialog = false })
    }

    MyApplicationTheme(dynamicColor = false) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            onClick = { showDialog = true },
            modifier = if (isCompact) {
                Modifier
                    .fillMaxWidth() // Fill the maximum width available
                    .height(80.dp)
            }// Keep the height as 100.dp
            else {
                Modifier
                    .width(400.dp) // Fill the maximum width available
                    .height(80.dp)
            }
        ) {
            Column {
                Row(


                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_alarm),
                        contentDescription = "",
                        modifier = Modifier.padding(top = 2.dp),

                        )
                    Text(
                        text = name,
                        modifier = Modifier.padding(start = 16.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                    )
                }

                Text(
                    text = "Status", fontSize = 14.sp, modifier = Modifier.padding(start = 56.dp),
                    color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Light
                )
            }


        }


    }
}
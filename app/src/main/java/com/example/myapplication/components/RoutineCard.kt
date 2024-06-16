package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme

import com.example.myapplication.ui.theme.YellowR

@Preview
@Composable
fun RoutineCard(
    expanded: Boolean = false, name: String = "Rutina",
    description: String = "Descripción de la rutina",
    icon: ImageVector = ImageVector.vectorResource(R.drawable.ic_devices),
    color: Color = YellowR
) {
    MyApplicationTheme(dynamicColor = false) {

        if (!expanded) {
            Card(
                onClick = {},
                colors = CardDefaults.cardColors(
                    containerColor = color,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .height(80.dp) // Keep the height as 100.dp
                    .width(100.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = name,
                        modifier = Modifier.padding(top = 2.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                    )
                }
            }

        } else {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth() // Fill the maximum width available
                    .height(80.dp) // Keep the height as 100.dp

            ) {
                Row {
                    Column(
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(color),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "",
                            modifier = Modifier.padding(start = 34.dp, end = 36.dp),

                            )
                        Text(
                            text = name,
                            modifier = Modifier.padding(start = 0.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                        )

                    }

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = description,
                            modifier = Modifier.padding(start = 16.dp, top = 10.dp, end = 16.dp),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp,
                            lineHeight = 16.sp,
                        )
                    }
                }


            }
        }
    }


}
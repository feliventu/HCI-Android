package com.example.homechan.ui.destinations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homechan.R

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun AboutDestination() {

    isLandscape(LocalContext.current)
    var paddingTop = if (isLandscape(LocalContext.current)) 12.dp else 50.dp
    Column(
        modifier = Modifier
            .padding(top = paddingTop)
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        Text(
            text = "Home Chan", fontSize = 30.sp, fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary
        )
        Text("Version 1.0", color = MaterialTheme.colorScheme.tertiary)

        Text(
            stringResource(id = R.string.materia),
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text("HCI", color = MaterialTheme.colorScheme.tertiary)

        Text(
            stringResource(id = R.string.developed),
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(stringResource(R.string.team_name), color = MaterialTheme.colorScheme.tertiary)



        Text(
            stringResource(id = R.string.date),
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text("21/06/2024", color = MaterialTheme.colorScheme.tertiary)

    }
}
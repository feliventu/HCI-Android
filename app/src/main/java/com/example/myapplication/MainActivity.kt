package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.flow.asFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {

                }
            }
        }
    }




enum class MyAppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
){
    HOME(R.string.home, Icons.Default.Home,R.string.home),
    DEVICES(R.string.devices, Icons.Default.Home,R.string.devices),
    RUTINAS(R.string.routines, Icons.Default.Call,R.string.routines),
}




@Composable
fun MyNavigationScaffold(){
    val adaptiveInfo = currentWindowAdaptiveInfo();
    val customNavSuiteType = with(adaptiveInfo){
        if(windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT){
           NavigationSuiteType.NavigationBar;
        } else {
          //NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
            NavigationSuiteType.NavigationDrawer
        }
    }

    var currentDestination by rememberSaveable { mutableStateOf(MyAppDestinations.HOME) }


    NavigationSuiteScaffold(
        navigationSuiteItems =
        {
            MyAppDestinations.entries.forEach{
            item(
                icon = {
                    Icon(imageVector = it.icon,
                        contentDescription = stringResource(it.contentDescription)
                        )
                },
                label = { Text(stringResource(it.label)) },
                selected = it == currentDestination,
                onClick = { currentDestination = it }
            )
            }
        },
        layoutType = customNavSuiteType,
        ) {
            when(currentDestination) {
                MyAppDestinations.HOME -> MyHomeDestination();
                MyAppDestinations.DEVICES -> MyDeviceDestination();
                MyAppDestinations.RUTINAS -> MyRoutineDestination();
            }
        }
    
}

@Composable
fun MyHomeDestination(){
    Text(text = "Home")
}

@Composable
fun MyDeviceDestination(){
    Text(text = "Devices")
}

@Composable
fun MyRoutineDestination(){
    Text(text = "Routines")
}

@Preview(showBackground = true)
@Composable
fun MyNavigationScaffoldPreview(){
    MyNavigationScaffold();
}

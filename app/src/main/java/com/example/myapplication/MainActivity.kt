package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBarDefaults.containerColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.myapplication.ui.theme.MyApplicationTheme

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
    @DrawableRes val icon: Int,
    @DrawableRes val filledIcon: Int,
    @StringRes val contentDescription: Int
){
    HOME(R.string.home, R.drawable.ic_home, R.drawable.ic_home_fill, R.string.home),
    DEVICES(R.string.devices, R.drawable.ic_devices, R.drawable.ic_devices_fill, R.string.devices),
    RUTINAS(R.string.routines, R.drawable.ic_routines, R.drawable.ic_routines_fill, R.string.routines),
}




@Composable
fun MyNavigationScaffold(){

        val adaptiveInfo = currentWindowAdaptiveInfo();
        val customNavSuiteType = with(adaptiveInfo) {
            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
                NavigationSuiteType.NavigationBar;
            } else {
                //NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
                NavigationSuiteType.NavigationDrawer
            }
        }

        var currentDestination by rememberSaveable { mutableStateOf(MyAppDestinations.HOME) }

    val itemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemColors(
            selectedIconColor = Color.Black,
            unselectedIconColor = Color.Gray,
            selectedIndicatorColor = Color.Transparent,
            unselectedTextColor = Color.Gray,
            selectedTextColor = Color.Black,
            disabledIconColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors()
    )
        NavigationSuiteScaffold(

            navigationSuiteItems =
            {

                MyAppDestinations.entries.forEach {
                    item(

                        colors = itemColors,


                        icon = {
                            val icon = if (it == currentDestination) {
                                ImageVector.vectorResource(id = it.filledIcon)
                            } else {
                                ImageVector.vectorResource(id = it.icon)
                            }
                            Icon(
                                imageVector = icon,
                                contentDescription = stringResource(it.contentDescription),
                                modifier = Modifier.size(32.dp)
                            )
                        },

                        label = { Text(stringResource(it.label)) },
                        selected = it == currentDestination,
                        onClick = { currentDestination = it }

                    )
                }
            },
            layoutType = customNavSuiteType,
            navigationSuiteColors = NavigationSuiteDefaults.colors(
               navigationBarContentColor = Color.Transparent,
               navigationBarContainerColor = Color.White,


            ),




            
            content = {
                TopBarFloatingButtonScaffold()
                when (currentDestination) {
                    MyAppDestinations.HOME -> MyHomeDestination();
                    MyAppDestinations.DEVICES -> MyDeviceDestination();
                    MyAppDestinations.RUTINAS -> MyRoutineDestination();
                }
            },

            )

        }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarFloatingButtonScaffold() {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Home-Chan")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Favorite"
                        )
                    }

                }
            )

        },
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = { /*asd*/ },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary,
                shape = CircleShape,
                modifier = Modifier.size(50.dp)
            ) {
                Icon(Icons.Filled.Add, "Small floating action button.")
            }
        },

        ) { innerPadding ->
        Text(
            text = "",
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun MyHomeDestination(){
    Box(modifier = Modifier.padding(vertical = 70.dp)){
        Text(text = "Home")
    }

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

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType.Companion.NavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme ( dynamicColor = false) {
            MainScaffold()

            }
        }
    }
}


enum class MyAppDestinations(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val filledIcon: Int,
    @StringRes val contentDescription: Int
) {
    HOME(R.string.home, R.drawable.ic_home, R.drawable.ic_home_fill, R.string.home),
    DEVICES(R.string.devices, R.drawable.ic_devices, R.drawable.ic_devices_fill, R.string.devices),
    RUTINAS(
        R.string.routines,
        R.drawable.ic_routines,
        R.drawable.ic_routines_fill,
        R.string.routines
    ),
}


/*@Composable
fun MyNavigationScaffold() {

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



        ) {
        TopBarFloatingButtonScaffold()
        Spacer(modifier = Modifier.height(100.dp))
        when (currentDestination) {
            MyAppDestinations.HOME -> MyHomeDestination();
            MyAppDestinations.DEVICES -> MyDeviceDestination();
            MyAppDestinations.RUTINAS -> MyRoutineDestination();
        }
    }

}*/



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold() {
    var currentDestination by rememberSaveable { mutableStateOf(MyAppDestinations.HOME) }
    val itemColors = NavigationBarItemColors(

            selectedIconColor = Color.Black,
            unselectedIconColor = Color.Gray,
            selectedIndicatorColor = Color.Transparent,
            unselectedTextColor = Color.Gray,
            selectedTextColor = Color.Black,
            disabledIconColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
        )



    Scaffold(
        bottomBar = {

            NavigationBar(
            contentColor = Color.Transparent,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.shadow(elevation = 15.dp),
        ) {
            MyAppDestinations.entries.forEach {
                NavigationBarItem(

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

        }

                    },

        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = Color.Black,

                ),
                title = {
                    Text("Home Chan", fontWeight = FontWeight.Bold)
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_more),
                            contentDescription = "Mas",
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                }
            )

        },
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = {  },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = Color.Black,
                shape = CircleShape,
                modifier = Modifier
                    .size(50.dp)
                    .shadow(elevation = 3.dp, shape = CircleShape),

            ) {
                Icon(Icons.Filled.Add, "Small floating action button.")
            }
        },

        containerColor = MaterialTheme.colorScheme.background,

        ) { innerPadding ->
    Column(
        modifier = Modifier
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        when (currentDestination) {
            MyAppDestinations.HOME -> MyHomeDestination();
            MyAppDestinations.DEVICES -> MyDeviceDestination();
            MyAppDestinations.RUTINAS -> MyRoutineDestination();
        }
    }
}
}



@Preview(showBackground = true)
@Composable
fun MyNavigationScaffoldPreview() {
    MyApplicationTheme(dynamicColor = false) {
        MainScaffold()
    }

}



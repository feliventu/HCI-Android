package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme(dynamicColor = false) {
                MyNavigationScaffold()

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
    HOME(R.string.nav_home, R.drawable.ic_home, R.drawable.ic_home_fill, R.string.nav_home),
    DEVICES(
        R.string.nav_devices,
        R.drawable.ic_devices,
        R.drawable.ic_devices_fill,
        R.string.nav_devices
    ),
    RUTINAS(
        R.string.nav_routines,
        R.drawable.ic_routines,
        R.drawable.ic_routines_fill,
        R.string.nav_routines
    ),
    NEW_HOME(R.string.plus_new_home, R.drawable.ic_home, R.drawable.ic_home_fill, R.string.plus_new_home)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
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
    var menuExpanded by rememberSaveable { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (menuExpanded) 360f else 0f, label = ""
    )

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
                if(it != MyAppDestinations.HOME &&
                    it != MyAppDestinations.DEVICES &&
                    it != MyAppDestinations.RUTINAS ){
                    return@forEach
                }
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

        Scaffold(
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
                        IconButton(onClick = { }) {
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
                Box {
                    AnimatedVisibility(
                        visible = !menuExpanded,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        if( currentDestination != MyAppDestinations.NEW_HOME){
                        SmallFloatingActionButton(
                            onClick = { menuExpanded = !menuExpanded },
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = Color.Black,
                            shape = CircleShape,
                            modifier = Modifier
                                .size(50.dp)
                                .shadow(elevation = 2.dp, shape = CircleShape)
                        ) {
                            Icon(Icons.Filled.Add, "Small floating action button.")
                        }}
                    }

                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                        shape = RoundedCornerShape(16.dp),
                        containerColor = Color.White,
                        offset = DpOffset(0.dp, 55.dp),
                    ) {
                        DropdownMenuItem(onClick = { /* Handle new device! */ menuExpanded = false
                        },
                            text = { Text(stringResource(id = R.string.plus_new_device)) })
                        DropdownMenuItem(onClick = { /* Handle new routine! */ menuExpanded =
                            false
                        },
                            text = { Text(stringResource(id = R.string.plus_new_routine)) })
                        HorizontalDivider(
                            thickness = 2.dp,
                            color = Color.Gray,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        DropdownMenuItem(onClick = { currentDestination = MyAppDestinations.NEW_HOME
                            menuExpanded = false },
                            text = { Text(stringResource(id = R.string.plus_new_home)) })
                        Icon(
                            Icons.Filled.Add, tint = Color.Black, modifier = Modifier
                                .align(
                                    Alignment.End
                                )
                                .padding(end = 13.dp, bottom = 10.dp).graphicsLayer(rotationZ = rotation),
                            contentDescription = ""
                        )
                    }
                }
            },
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
                    MyAppDestinations.NEW_HOME -> NewHomeDestination();
                }

            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun MyNavigationScaffoldPreview() {
    MyApplicationTheme(dynamicColor = false) {
        MyNavigationScaffold()
    }
}



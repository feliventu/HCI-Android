package com.example.homechan

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.homechan.ui.destinations.AboutDestination
import com.example.homechan.ui.destinations.DevicesDestination
import com.example.homechan.ui.destinations.MyHomeDestination
import com.example.homechan.ui.destinations.MyRoutineDestination
import com.example.homechan.ui.destinations.NewHomeDestination
import com.example.homechan.ui.theme.Green01
import com.example.homechan.ui.theme.MyApplicationTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var instance: MainActivity
    }

    private lateinit var notificationHelper: NotificationHelper

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        notificationHelper = NotificationHelper.getInstance(this@MainActivity)
        askForNotificationPermission()


        enableEdgeToEdge()
        setContent {
            MyApplicationTheme(dynamicColor = false) {
                MyCustomNavigationScaffold()

            }
        }


    }

    private fun askForNotificationPermission() {
        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        1
                    )
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)
                }
                println("check permission")
                println(areNotificationsEnabled())
                return@with
            }
        }
    }


}

class NotificationHelper private constructor(private val context: Context) {

    companion object {
        private var INSTANCE: NotificationHelper? = null

        fun getInstance(context: Context): NotificationHelper {
            if (INSTANCE == null) {
                INSTANCE = NotificationHelper(context.applicationContext)
            }
            return INSTANCE!!
        }
    }

    private val CHANNEL_ID = "main_channel"

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notificaciones de HomeChan"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)

            channel.enableVibration(true)
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
            Log.d("Notification", "Channel was created")
        } else {
            Log.d("Notification", "Channel was not needed")
        }
    }

    @SuppressLint("MissingPermission")
    fun sendNotification(id: Int, title: String, message: String) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_hc)

            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(id, builder.build())
            Log.d("Notification", "Notification was sent")
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

    NEW_HOME(R.string.new_home, R.drawable.ic_home, R.drawable.ic_home_fill, R.string.new_home),
    ABOUT(R.string.about, R.drawable.ic_about, R.drawable.ic_about_fill, R.string.about)

}


@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCustomNavigationScaffold() {
    Box(modifier = Modifier.fillMaxSize()) {
        MyNavigationScaffold()
        val adaptiveInfo = currentWindowAdaptiveInfo();
        if (adaptiveInfo.windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT)

            Box(modifier = Modifier.size(250.dp).align(Alignment.BottomStart)) {
        Text(
            text = "HomeChan",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp),
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.surface,
            fontWeight = FontWeight.Bold
        )
    }}
    }



@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavigationScaffold() {

    val adaptiveInfo = currentWindowAdaptiveInfo();
    val isCompact =
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
    val customNavSuiteType = with(adaptiveInfo) {
        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
            NavigationSuiteType.NavigationBar;
        } else {

            NavigationSuiteType.NavigationDrawer
        }
    }

    var currentDestination by rememberSaveable { mutableStateOf(MyAppDestinations.HOME) }
    var menuExpanded by rememberSaveable { mutableStateOf(false) }
    var menuExpandedTop by rememberSaveable { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (menuExpanded) 360f else 0f, label = ""
    )

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    val itemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemColors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.tertiary,
            selectedIndicatorColor = Color.Transparent,
            unselectedTextColor = MaterialTheme.colorScheme.tertiary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            disabledIconColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.tertiary,
            selectedContainerColor = MaterialTheme.colorScheme.background,
            unselectedContainerColor = Color.Transparent,
            unselectedTextColor = MaterialTheme.colorScheme.tertiary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
        )
    )

    NavigationSuiteScaffold(

        navigationSuiteItems =
        {


            MyAppDestinations.entries.forEachIndexed { index, it ->
                if (it != MyAppDestinations.HOME &&
                    it != MyAppDestinations.DEVICES &&
                    (it != MyAppDestinations.ABOUT || customNavSuiteType != NavigationSuiteType.NavigationDrawer)
                ) {
                    return@forEachIndexed
                }

                item(
                    modifier = if (!isCompact && index == 0) {
                        Modifier.width(250.dp).padding(horizontal = 16.dp)
                    } else if (!isCompact) {
                        Modifier.width(250.dp).padding(horizontal = 16.dp)
                    } else {
                        Modifier
                    },
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
            navigationBarContainerColor = MaterialTheme.colorScheme.inversePrimary,
            navigationDrawerContentColor = Color.Transparent,
            navigationDrawerContainerColor = MaterialTheme.colorScheme.inversePrimary,

            ),


        ) {
        Box {
            Text(
                text = "HomeChan",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState) { data ->

                    if (data.visuals.message.contains("Error")) {
                        Snackbar(
                            snackbarData = data,
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = Color.Black,
                            dismissActionContentColor = Color.Black,
                        )
                    } else {

                        Snackbar(
                            snackbarData = data,
                            containerColor = Green01,
                            contentColor = Color.Black,
                            dismissActionContentColor = Color.Black,
                        )
                    }
                }
            },
            topBar = {
                if (isCompact) {
                    var iconButtonPosition by remember { mutableStateOf(Offset.Zero) }
                    var xOffset by remember { mutableStateOf(0f) }
                    var yOffset by remember { mutableStateOf(0f) }
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.inversePrimary,
                            titleContentColor = MaterialTheme.colorScheme.primary,

                            ),
                        title = {
                            Text("Home Chan", fontWeight = FontWeight.Bold)
                        },
                        actions = {
                            IconButton(onClick = { menuExpandedTop = !menuExpandedTop },
                                modifier = Modifier.onGloballyPositioned { coordinates ->
                                    val position = coordinates.positionInRoot()
                                    iconButtonPosition = Offset(
                                        position.x.roundToInt().toFloat(),
                                        position.y.roundToInt().toFloat()
                                    )
                                    xOffset = position.x
                                    yOffset = position.y
                                }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_more),
                                    contentDescription = "Mas",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(28.dp)
                                )
                            }

                        }
                    )

                    DropdownMenu(
                        modifier = Modifier.padding(start = 10.dp),
                        expanded = menuExpandedTop,
                        onDismissRequest = { menuExpandedTop = false },
                        shape = RoundedCornerShape(16.dp),
                        containerColor = MaterialTheme.colorScheme.inversePrimary,
                        offset = DpOffset(xOffset.dp - 20.dp, yOffset.dp),
                    ) {
                        DropdownMenuItem(onClick = {
                            currentDestination = MyAppDestinations.ABOUT
                            menuExpandedTop = false
                        },
                            text = {
                                Text(
                                    stringResource(id = R.string.about),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            })
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
                    MyAppDestinations.HOME -> MyHomeDestination(snackbarHostState = snackbarHostState)
                    MyAppDestinations.DEVICES -> DevicesDestination(snackbarHostState = snackbarHostState)
                    MyAppDestinations.NEW_HOME -> NewHomeDestination();
                    MyAppDestinations.ABOUT -> AboutDestination();
                }

            }

        }
    }
}


@RequiresApi(Build.VERSION_CODES.R)
@Preview(showBackground = true)
@Composable
fun MyNavigationScaffoldPreview() {
    MyApplicationTheme(dynamicColor = false) {
        MyNavigationScaffold()
    }
}



package com.example.homechan.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    secondary = Green02,
    tertiary = Color.Gray,
    background = Color(0xFF161616), //fondo de la app
    surface = Color(0xFF444444),    //borde de los botones
    onSurface = Color.LightGray, //borde del textfield
    primaryContainer = Color(0xFF222222),   //la card
    inversePrimary = Color.Black,
    onTertiary = Color(0x0FD6D6D6), //divider
    error = Color(0xFFFC867F),
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Black, //texto
    secondary = Green02,    //switch prendido
    tertiary = Color.Gray,  //cosas terciarias
    background = Color(0xfff8f8f8), //fondo de la app
    surface = LightGray01,    //borde de los botones
    onSurface = Color.LightGray, //borde del textfield
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color(0xffECECEC), //divider
    onBackground = Color(0xFF1C1B1F),
    primaryContainer = Color(0xFFE6E6E6),   //la card
    onPrimaryContainer = onPrimaryContainerLight,

    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,

    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = Color(0xFFFC867F),
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,

    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = Color.White,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
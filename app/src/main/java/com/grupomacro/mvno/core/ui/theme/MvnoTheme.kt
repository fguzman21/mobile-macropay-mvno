package com.grupomacro.mvno.core.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun MvnoTheme(
    isFullScreen: Boolean = false,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColorEnabled: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        isDynamicColorEnabled && supportDynamicColor -> {
            val context = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        useDarkTheme -> {
            DarkColorScheme
        }

        else -> {
            LightColorScheme
        }
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.primary.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                    useDarkTheme
            }
        }
        if (isFullScreen) {
            SideEffect {
                val window = (view.context as? Activity)?.window
                if (window != null) {
                    WindowCompat.setDecorFitsSystemWindows(window, false)
                    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !useDarkTheme
                    window.statusBarColor = Color.Transparent.toArgb()
                    window.navigationBarColor = Color.Black.toArgb()
                }
            }
        }
    }
    val customColors = if (useDarkTheme) DarkCustomColors else LightCustomColors
    CompositionLocalProvider(
        LocalCustomColors provides customColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

private val supportDynamicColor: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

private val LightColorScheme = lightColorScheme(
    primary = pinkMacropay,
    secondary = yellowMacropay,
    tertiary = greenLight,
    background = black,
    onBackground = transparent,
    error = redLight,
)

private val DarkColorScheme = darkColorScheme(
    primary = pinkMacropay,
    secondary = yellowMacropay,
    tertiary = greenLight,
    background = black,
    onBackground = transparent,
    error = redLight,
)

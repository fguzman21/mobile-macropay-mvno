package com.grupomacro.mvno.screens.help.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.grupomacro.mvno.core.ui.composables.BackgroundScreen
import com.grupomacro.mvno.core.ui.theme.MvnoTheme

@Composable
fun HelpScreen(launchSection: (Int) -> Unit) {
        MvnoTheme {
            Scaffold(

            ) { paddingValues ->
                BackgroundScreen(Modifier.padding(paddingValues)) {
                    HelpContent(launchSection)
                }
            }
        }
}

@Composable
fun HelpContent( launchSection: (Int) -> Unit) {
    Text("PANTALLA AYUDA",
        color = Color.Red)
}

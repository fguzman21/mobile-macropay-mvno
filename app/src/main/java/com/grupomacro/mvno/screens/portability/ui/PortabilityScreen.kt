package com.grupomacro.mvno.screens.portability.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.grupomacro.mvno.core.ui.composables.BackgroundScreen
import com.grupomacro.mvno.core.ui.theme.MvnoTheme

@Composable
fun PortabilityScreen() {
    MvnoTheme {
        Scaffold(

        ) { paddingValues ->
            BackgroundScreen(Modifier.padding(paddingValues)) {
                PortabilityContent()
            }
        }
    }
}

@Composable
fun PortabilityContent() {
    Text("PANTALLA PORTABILIDAD",
        color = Color.Red)
}

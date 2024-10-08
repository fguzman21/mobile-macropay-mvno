package com.grupomacro.mvno.screens.recharges.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.grupomacro.mvno.core.ui.composables.BackgroundScreen
import com.grupomacro.mvno.core.ui.theme.MvnoTheme


@Composable
fun RechargesScreen() {
    MvnoTheme {
        Scaffold(

        ) { paddingValues ->
            BackgroundScreen(Modifier.padding(paddingValues)) {
                RechargesContent()
            }
        }
    }
}

@Composable
fun RechargesContent() {
    Text("PANTALLA RECARGAS Y PLANES",
        color = Color.Red)
}

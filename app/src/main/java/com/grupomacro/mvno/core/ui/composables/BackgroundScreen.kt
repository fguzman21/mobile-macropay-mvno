package com.grupomacro.mvno.core.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.grupomacro.mvno.core.ui.theme.customColors

@Composable
fun BackgroundScreen(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = modifier
            .then(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.customColors.backgroundGrayScreen)
            ),
        content = content
    )
}

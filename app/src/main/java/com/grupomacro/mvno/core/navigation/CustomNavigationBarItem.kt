package com.grupomacro.mvno.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grupomacro.mvno.core.navigation.NavigationBarEntry.Companion.navigationBarItems

@Composable
fun RowScope.CustomNavigationBarItem(
    navigationItem: NavigationBarEntry,
    isSelected: Boolean,
    onClick: () -> Unit,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
            .clickable(onClick = onClick)
    ) {
        if (isSelected) {
            SelectedIndicator()
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                LocalContentColor.current.copy(alpha = 0.6.toFloat())
            }
            Icon(
                imageVector = ImageVector.vectorResource(id = navigationItem.icon),
                contentDescription = "section",
                modifier = Modifier
                    .padding(top = 4.dp)
                    .size(24.dp),
                tint = color,
            )
            Text(
                text = stringResource(id = navigationItem.label),
                color = color,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
private fun BoxScope.SelectedIndicator() {
    Spacer(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(2.dp),
            )
            .size(50.dp, 2.dp)
            .align(Alignment.TopCenter)
    )
}

@Preview
@Composable
fun MyPreview() {
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier.height(56.dp),
                containerColor = Color.Green
            ) {
                navigationBarItems.forEach { item ->
                    CustomNavigationBarItem(
                        navigationItem = item,
                        isSelected = item.appScreen == Recharges,
                        onClick = {},
                    )
                }
            }
        }
    ) { contentPadding ->
        Text(
            text = "Hello, world!",
            modifier = Modifier.padding(contentPadding)
        )
    }
}

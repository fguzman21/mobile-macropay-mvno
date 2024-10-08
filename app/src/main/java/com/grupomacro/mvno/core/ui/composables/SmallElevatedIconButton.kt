package com.grupomacro.mvno.core.ui.composables

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.grupomacro.mvno.core.ui.theme.customColors

@Composable
fun SmallElevatedIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.customColors.buttonSmallCircularBackground,
    contentColor: Color = MaterialTheme.customColors.buttonSmallCircularContent,
) {
    SmallFloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        containerColor = containerColor,
        contentColor = contentColor,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
        )
    }
}

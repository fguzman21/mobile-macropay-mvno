package com.grupomacro.mvno.core.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.grupomacro.mvno.R

@Composable
fun NormalButtonContent(
    @StringRes buttonLabel: Int,
    imageVectorStart: ImageVector? = null,
    imageVectorEnd: ImageVector? = null,
    fontWeight: FontWeight = FontWeight.SemiBold,
    color: Color = MaterialTheme.colorScheme.primary,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (imageVectorStart != null) {
            Icon(
                imageVector = imageVectorStart,
                contentDescription = "Regresar",
                tint = color,
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.marginV05x))
            )
        }
        Text(
            text = stringResource(buttonLabel),
            fontWeight = fontWeight,
            fontSize = fontSize,
            color = color,
            style = MaterialTheme.typography.labelMedium,
        )
        if (imageVectorEnd != null) {
            Icon(
                imageVector = imageVectorEnd,
                contentDescription = "Continuar",
                tint = color,
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.marginV05x))
            )
        }
    }
}

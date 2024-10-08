package com.grupomacro.mvno.core.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.grupomacro.mvno.R

@Composable
fun Modifier.paddingVerticalChainElement(
    top: Dp = dimensionResource(id = R.dimen.marginV1x),
    start: Dp = dimensionResource(id = R.dimen.marginH1x),
    end: Dp = dimensionResource(id = R.dimen.marginH1x),
): Modifier {
    val padding = Modifier.padding(
        top = top,
        start = start,
        end = end,
    )
    return this.then(padding)
}

@Composable
fun Modifier.paddingNormalButton(
    top: Dp = dimensionResource(id = R.dimen.marginV15x),
    bottom: Dp = dimensionResource(id = R.dimen.marginV5x),
    start: Dp = dimensionResource(id = R.dimen.marginH2x),
    end: Dp = dimensionResource(id = R.dimen.marginH2x),
): Modifier {
    val padding = Modifier
        .padding(
            top = top,
            start = start,
            end = end,
            bottom = bottom,
        )
        .fillMaxWidth()
        .height(dimensionResource(id = R.dimen.normal_button_height))
    return this.then(padding)
}

@Composable
fun Modifier.paddingSmallRoundButton(): Modifier {
    val padding = Modifier
        .padding(
            start = dimensionResource(id = R.dimen.marginV05x),
        )
        .size(dimensionResource(id = R.dimen.small_circular_button_default_size))
    return this.then(padding)
}

@Composable
fun Modifier.paddingSmallLogoButton(): Modifier {
    val padding = Modifier
        .padding(
            start = dimensionResource(id = R.dimen.marginV05x),
        )
        .height(dimensionResource(id = R.dimen.small_appbar_logo_default_height))
    return this.then(padding)
}

@Composable
fun Modifier.paddingSmallRoundActionButton(): Modifier {
    val padding = Modifier
        .padding(
            end = dimensionResource(id = R.dimen.marginV05x),
        )
        .size(dimensionResource(id = R.dimen.small_circular_button_default_size))
    return this.then(padding)
}

@Composable
fun Modifier.paddingSmallRightRoundButton(): Modifier {
    val padding = Modifier
        .padding(
            end = dimensionResource(id = R.dimen.marginV05x),
        )
        .size(dimensionResource(id = R.dimen.small_circular_button_default_size))
    return this.then(padding)
}

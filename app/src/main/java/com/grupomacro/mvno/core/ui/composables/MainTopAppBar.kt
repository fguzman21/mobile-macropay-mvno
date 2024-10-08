package com.grupomacro.mvno.core.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.grupomacro.mvno.R
import com.grupomacro.mvno.core.navigation.Home
import com.grupomacro.mvno.core.ui.theme.MvnoTheme
import com.grupomacro.mvno.core.ui.theme.customColors
import com.grupomacro.mvno.domain.session.model.PartOfDayEnum
import com.grupomacro.mvno.screens.container.presentation.model.ContainerUiState

@Composable
fun MainTopAppBar(
    uiState: ContainerUiState,
    navController: NavHostController?,
    onNotificationsButtonPressed: () -> Unit,
    onLogoButtonPressed: () -> Unit = { },
) {
    Surface(
        shadowElevation = 0.dp
    ) {
        val navBackStackEntry = navController?.currentBackStackEntryAsState()
        val isHome = navBackStackEntry?.value?.destination?.hasRoute<Home>() ?: false
        CenterAlignedTopAppBar(
            title = { },
            navigationIcon = {
                if (isHome) { //TODO("Estos datos deben venir del viewmodel")
                    MacropayGreetings(getGreeting(uiState.partOfDay), uiState.userDisplayedName)
                } else {
                    MacropayLogo()
                }
            },
            actions = {
                NotificationsButton("${uiState.notificationsCounting}", onNotificationsButtonPressed)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
        )
    }
}

@Composable
private fun MacropayLogo() {
    Image(
        painter = painterResource(id = R.drawable.logo_macropay_top),
        contentDescription = null,
        modifier = Modifier
            .paddingSmallLogoButton()
    )
}

@Composable
private fun MacropayGreetings(
    greetings: String,
    userName: String,
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(start = dimensionResource(id = R.dimen.marginV05x))
            .wrapContentSize()
    ) {
        val (img, txtTop, txtBottom) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.img_sim_avatar),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 12.dp)
                .size(48.dp)
                .constrainAs(img) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                },
        )
        Text(
            text = greetings,
            modifier = Modifier
                .constrainAs(txtTop) {
                    top.linkTo(parent.top)
                    bottom.linkTo(txtBottom.top)
                    start.linkTo(img.end)
                },
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        )
        Text(
            text = userName,
            modifier = Modifier
                .constrainAs(txtBottom) {
                    top.linkTo(txtTop.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(img.end)
                },
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun NotificationsButton(
    alertContent: String,
    onActionButtonPressed: () -> Unit
) {
    ConstraintLayout(
        createConstraintsNotificationsButton()
    ) {
        SmallElevatedIconButton(
            onClick = onActionButtonPressed,
            imageVector = Icons.Outlined.Notifications,
            modifier = Modifier
                .paddingSmallRoundActionButton()
                .layoutId("button"),
            containerColor = MaterialTheme.colorScheme.secondary,
        )
        if (alertContent.isNotEmpty()) {
            Text(
                text = "",
                modifier = Modifier
                    .offset(x = 6.dp)
                    .size(18.dp)
                    .background(
                        color = colorResource(id = R.color.colorBlueMacropayNotificationDark),
                        shape = CircleShape,
                    )
                    .layoutId("back"),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
            Text(
                text = alertContent.take(1),
                modifier = Modifier
                    .offset(x = 6.dp)
                    .layoutId("number"),
                color = Color.White,
                fontSize = 12.sp,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
        }
    }
}

private fun createConstraintsNotificationsButton(): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val back = createRefFor("back")
        val number = createRefFor("number")
        constrain(back) {
            centerVerticallyTo(button)
            end.linkTo(button.start)
        }
        constrain(number) {
            centerHorizontallyTo(back)
            centerVerticallyTo(back)
        }
    }
}

@Composable
private fun getGreeting(partOfDay: PartOfDayEnum): String {
    val res = when (partOfDay) {
        PartOfDayEnum.DAY -> R.string.greeting_day
        PartOfDayEnum.AFTERNOON -> R.string.greeting_afternoon
        PartOfDayEnum.NIGHT -> R.string.greeting_nigth
    }
    return stringResource(res)
}

@Preview
@Composable
private fun MyPreview() {
    MvnoTheme {
        MainTopAppBar(
            ContainerUiState.initialState,
            null,
            {},
            {}
        )
    }
}

package com.grupomacro.mvno.screens.home.ui

import android.Manifest
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.NoteAdd
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.grupomacro.mvno.R
import com.grupomacro.mvno.core.ui.composables.BackgroundScreen
import com.grupomacro.mvno.core.ui.composables.OtherButton
import com.grupomacro.mvno.core.ui.composables.paddingNormalButton
import com.grupomacro.mvno.core.ui.theme.MvnoTheme
import com.grupomacro.mvno.core.ui.theme.customColors
import com.grupomacro.mvno.screens.home.ui.composables.AvailablePlanComponent
import com.grupomacro.mvno.screens.home.ui.composables.ChargingPointsComponent
import com.grupomacro.mvno.screens.home.ui.composables.CurrentPlanComponent

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
   /* viewModel: HomeViewModel = hiltViewModel(),
    viewModelQuitar: StoresViewModel = hiltViewModel()*/
) {
   // val uiState by viewModel.uiState.collectAsStateWithLifecycle()
   // LaunchLifecycleActions(viewModel, HomeAction.ScreenStartedAction, HomeAction.ScreenStoppedAction)
    MvnoTheme(isFullScreen = false) {
        BackgroundScreen {
            ConstraintLayout(
                createConstrains(), modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .paint(
                        painter = painterResource(id = R.drawable.img_home_header),
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.FillWidth,
                    )
            ) {
                HomeScreenContent()
            }

        }
    }
}


@Composable
private fun HomeScreenContent(){

    Box(modifier = Modifier
        .padding(dimensionResource(R.dimen.marginStandar))
        .layoutId("bannerButton"),
        contentAlignment = Alignment.Center,) {
        Column{
            Image(
                painter = painterResource(id = R.drawable.img_btn_backmacro),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .layoutId("imgButtonApp")
                    .fillMaxWidth()
                    .height(120.dp),
            )

            CurrentPlanComponent(modifier = Modifier
                .fillMaxWidth()
                .layoutId("plansActive")
            )

            AddMoreComponent(modifier = Modifier
                .layoutId("addMore"))

            AvailablePlanComponent(modifier = Modifier
                .fillMaxWidth()
                .layoutId("plansActive")
            )

            ButtonMoreComponent(modifier = Modifier
                .layoutId("btnViewMore"))

            ChargingPointsComponent(modifier = Modifier
                .layoutId("chargingPoints"))

            Image(
                painter = painterResource(id = R.drawable.img_cambio_sim),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .layoutId("imgButtonApp")
                    .fillMaxWidth()
                    .height(150.dp),
            )

        }
    }
}



@Composable
fun AddMoreComponent(modifier : Modifier) {
   Row(modifier = modifier
       .fillMaxWidth()
       .padding(
           top = dimensionResource(R.dimen.marginV1x),
           bottom = dimensionResource(R.dimen.marginV1x)
       )){

       Icon(
           modifier = Modifier
               .size(36.dp),
           imageVector = Icons.Outlined.Add,
           contentDescription = "",
           tint = MaterialTheme.customColors.textFieldTopLabel,
       )

       Text(modifier = Modifier
           .fillMaxHeight()
           .align(Alignment.CenterVertically),
           text = stringResource(R.string.h_mas_datos),
           fontSize = 18.sp,
           fontWeight = FontWeight.Bold,
           color = MaterialTheme.customColors.textFieldTopLabel,
       )

   }
}


@Composable
fun ButtonMoreComponent(modifier: Modifier) {
    OtherButton(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .layoutId("btnOtroMomento")
            .padding(dimensionResource(R.dimen.marginV1x)),
        enabled = true,
        border = BorderStroke(
            1.dp,
            MaterialTheme.customColors.buttonRegularBackground
        )
    ) {
        Text(
            text = stringResource(id = R.string.havp_btn_ver_planes),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun createConstrains(): ConstraintSet {
    return ConstraintSet {
        val bannerButton = createRefFor("bannerButton")
        val plansActive = createRefFor("plansActive")
        val addMore = createRefFor("addMore")
        val chargingPoints = createRefFor("chargingPoints")
        val btnViewMore = createRefFor("btnViewMore")
        constrain(bannerButton) {
            top.linkTo(parent.top)
        }
        constrain(plansActive) {
            top.linkTo(bannerButton.bottom)
        }
        constrain(addMore) {
            top.linkTo(plansActive.bottom)
        }
        constrain(chargingPoints) {
            top.linkTo(addMore.bottom)
        }
        constrain(btnViewMore) {
            top.linkTo(chargingPoints.bottom)
        }
    }
}

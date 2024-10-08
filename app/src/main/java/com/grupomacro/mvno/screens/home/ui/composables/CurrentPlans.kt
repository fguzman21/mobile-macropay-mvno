package com.grupomacro.mvno.screens.home.ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grupomacro.mvno.R
import com.grupomacro.mvno.core.ui.theme.customColors

@Composable
fun CurrentPlanComponent(modifier: Modifier = Modifier) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf(stringResource(R.string.hp_mi_plan), stringResource(R.string.hp_mas_datos))


    ElevatedCard(
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()) {
            TabRow(
                modifier = Modifier
                    .fillMaxWidth(),
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color.White,
                contentColor = MaterialTheme.customColors.backgroundMacroPayGeneral,

            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(modifier = Modifier
                        .weight(1f),
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text =  title,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> PlanDetails(
                    type = stringResource(R.string.hp_datos_plan_actual),
                    planName = "MacroChip 50",
                    totalData = 6000,
                    usedData = 4800,
                    aviablePorcent = 20,
                    availableData = 1200,
                    validity = "02/06/2024",
                    expiresIn = "3 días"
                )

                1 -> PlanDetails(
                    type = stringResource(R.string.hp_datos_plan_adicional),
                    planName = "MacroChip internet 100",
                    totalData = 10000,
                    usedData = 0,
                    aviablePorcent = 100,
                    availableData = 10000,
                    validity = "14/11/2024",
                    expiresIn = "18 días"
                )
            }
        }

    }
}

@Composable
fun PlanDetails(
    type: String,
    planName: String,
    totalData: Int,
    usedData: Int,
    aviablePorcent: Int,
    availableData: Int,
    validity: String,
    expiresIn: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.marginStandar))
        ){
            Box(
                modifier = Modifier
                    .weight(.3f)
            ){
                Image(
                    painter = painterResource(id = R.drawable.icon_plan_actual),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                        .align(Alignment.TopStart),
                )
            }
            Column(
                modifier = Modifier
                    .weight(2f)
            ){
                Text(
                    text = type,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = planName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
        Divider(modifier = Modifier.fillMaxWidth())

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.marginH1x))
        ){
            Box(
                modifier = Modifier
                    .weight(1f)
            ){
                CustomSemiCircularProgressIndicator(aviablePorcent,100)
            }
            Column(
                modifier = Modifier
                    .weight(1f),

            ){
                Text(modifier = Modifier
                    .fillMaxWidth(),
                    text = stringResource(R.string.hp_datos_totales),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.End
                )
                Text(modifier = Modifier
                    .fillMaxWidth(),
                    text = "$totalData MB",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                )

                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.marginV05x)),
                    text = stringResource(R.string.hp_datos_usados),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.End
                )
                Text(modifier = Modifier
                    .fillMaxWidth(),
                    text = "$usedData MB",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                )
            }

        }
        Row(
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.marginH05x),
                    bottom = dimensionResource(R.dimen.marginH05x)
                )
        ){
            Text(
                text = stringResource(R.string.hp_ddatos_disponibles),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )
            Text(
                color = MaterialTheme.customColors.textTitle,
                text = "$availableData MB",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        }
        Divider(modifier = Modifier.fillMaxWidth())

        Row(
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.marginH1x),
                    bottom = dimensionResource(R.dimen.marginH05x)
                )
        ){
            Text(modifier = Modifier
                    .weight(1f),
                text = stringResource(R.string.hp_datos_vigencia),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start

            )
            Text(modifier = Modifier
                .weight(1.5f),
                text = "$validity",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start

            )
            Text(modifier = Modifier
                .weight(1.5f),
                text = stringResource(R.string.hp_datos_vence),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.End

            )
            Text(modifier = Modifier
                .weight(.8f),
                text = "$expiresIn",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End

            )
        }

    }
}



@Composable
fun CustomSemiCircularProgressIndicator(aviablePorcent: Int, totalData: Int) {
    val progress = (aviablePorcent.toFloat() / totalData).coerceIn(0f, 1f) // Limitar el progreso entre 0 y 1
    val density = LocalDensity.current
    val strokeWidth = with(density) { 14.dp.toPx() }
    var width = 150.dp
    val height = 150.dp

    Box(modifier = Modifier
        .width(150.dp)
        .height(100.dp),
        contentAlignment = Alignment.TopCenter
       ){
        Canvas(modifier = Modifier
            .fillMaxSize()){

            drawArc(
                color = Color(0xFFEDF2F7),
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                size = Size(width.toPx(),height.toPx()),
                style = Stroke(strokeWidth)
            )


            drawArc(
                color = Color(0xFF6200EE),
                startAngle = 180f,
                sweepAngle = 180f * progress,
                useCenter = false,
                size = Size(width.toPx(),height.toPx()),
                style = Stroke(strokeWidth)
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.marginH2x))){
            Text(modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.hp_datos_disponibles),
                color = MaterialTheme.customColors.textTitleSM,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
            Text(modifier = Modifier
                .fillMaxWidth(),
                text = "$aviablePorcent %",
                color = MaterialTheme.customColors.textTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

    }
}
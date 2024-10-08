package com.grupomacro.mvno.screens.home.ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grupomacro.mvno.R
import com.grupomacro.mvno.core.ui.composables.NormalButton
import com.grupomacro.mvno.core.ui.composables.NormalButtonContent
import com.grupomacro.mvno.core.ui.composables.TitleLargeText
import com.grupomacro.mvno.core.ui.composables.paddingNormalButton
import com.grupomacro.mvno.core.ui.theme.customColors

@Composable
fun AvailablePlanComponent(modifier: Modifier = Modifier) {
    ElevatedCard(
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = dimensionResource(R.dimen.marginStandar)
            )
            .height(280.dp)

    ) {

        Column(modifier = Modifier
            .fillMaxWidth()) {
                AvailablePlanItem("1","Macrochip 60", 80,7,60)
        }

    }

    ElevatedCard(
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier

            .fillMaxWidth()
            .padding(
                bottom = dimensionResource(R.dimen.marginStandar)
            )
            .height(280.dp)

    ) {

        Column(modifier = Modifier
            .fillMaxWidth()) {
            AvailablePlanItem("1","Macrochip 120", 120,15,80)
        }

    }
}

@Composable
fun AvailablePlanItem(
    type: String,
    planName: String,
    price: Int,
    days: Int,
    ammountGb: Int,

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
            Column(
                modifier = Modifier
                    .weight(1f)
            ){
                Text(modifier = Modifier
                    .fillMaxWidth(),
                    text = planName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal
                )
                Row(modifier = Modifier
                    .padding(
                        top = dimensionResource(R.dimen.marginV05x)
                    )) {
                    Text(
                        text = "$ $price ",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " / $days días",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f),
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_happy_macropay),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    alignment = Alignment.CenterEnd
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(R.dimen.marginV05x)
                        ),
                    text = "$ammountGb GB",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.customColors.textTitle,
                    textAlign = TextAlign.End
                )

            }

        }
        Divider(modifier = Modifier.fillMaxWidth())

        TitleLargeText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(R.dimen.marginV05x),
                    bottom =  dimensionResource(R.dimen.marginV05x)
                ),
            text = stringResource(R.string.havp_content),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.textFieldTopLabel,
            textAlign = TextAlign.Center
        )

        val socialIcons = listOf(
            R.drawable.ic_whatsapp,
            R.drawable.ic_facebook,
            R.drawable.ic_ig,
            R.drawable.ic_messenger,
            R.drawable.ic_x,
            R.drawable.ic_telegram,
            R.drawable.ic_snapchat
        )

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
            .padding(
                top = dimensionResource(R.dimen.marginV05x),
                bottom = dimensionResource(R.dimen.marginV05x)
            )
        ) {
            socialIcons.forEach { iconId ->
                Image(
                    painter = painterResource(id = iconId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(dimensionResource(R.dimen.marginV025x)),
                    alignment = Alignment.Center
                )
            }
        }

        NormalButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("btnRecargarAhora")
                .padding(
                    top = dimensionResource(R.dimen.marginV05x),
                    bottom = dimensionResource(R.dimen.marginV05x)
                )
            ,
            enabled = true,
        ) {
            NormalButtonContent(
                buttonLabel = R.string.havp_btn_recargar,
                color = Color.White
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()){
            Text(
                text = stringResource(R.string.havp_ley_one_recargar),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.customColors.textFieldTopLabel,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.havp_ley_two_recargar),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.textTitle,
                textAlign = TextAlign.Center
            )
        }

    }
}




package com.grupomacro.mvno.screens.home.ui.composables

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.android.gms.maps.model.LatLng
import com.grupomacro.mvno.R
import com.grupomacro.mvno.core.ui.composables.TitleMediumText
import com.grupomacro.mvno.core.ui.theme.customColors


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChargingPointsComponent(modifier: Modifier = Modifier) {
    // Lista de datos de las tiendas
    val stores = listOf(
        StoreData("Macropay", "Calle 27 núm. 156, Col. Buenavista", true, "Cierra a las 9pm"),
        StoreData("Otra Tienda", "Calle 10 núm. 20, Col. Centro", false, "Cierra a las 8pm")
        // Puedes añadir más aquí
    )

    // Columna principal
    Column(modifier = modifier) {
        TitleMediumText(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(R.string.havp_puntos),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.textFieldTopLabel,
            textAlign = TextAlign.Left
        )
        val pagerState = rememberPagerState(0)

        // Componente HorizontalPager para la navegación horizontal
        HorizontalPager(
            state = pagerState,
            count = stores.size,
            modifier = Modifier.fillMaxWidth(),
            itemSpacing = 0.dp,
            contentPadding = PaddingValues(start = 20.dp)
        ) { page ->
            CardItem(store = stores[page])
        }

        LaunchedEffect(key1 = Unit, block = {
            pagerState.animateScrollToPage(0,0.1f)
        })
    }
}
// Clase de datos para la tienda
data class StoreData(val name: String, val address: String, val open: Boolean, val closingTime: String)

@Composable
private fun CardItem(store: StoreData) {
    ElevatedCard(
        modifier = Modifier
            .width(320.dp) // Ajusta el tamaño según sea necesario
            .height(120.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen de la tienda
            CardStoreImage(painterResource(id = R.drawable.img_store_generic))
            // Datos de la tienda
            CardStoreData(store)
        }
    }
}

// Composable que muestra los datos de la tienda
@Composable
private fun CardStoreData(store: StoreData) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = store.name,
            color = MaterialTheme.customColors.buttonRegularBackground,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = store.address,
            color = MaterialTheme.customColors.textTitle,
            fontSize = 14.sp,
        )
        Row(modifier = Modifier.padding(bottom = 2.dp)) {
            Text(
                text = if (store.open) stringResource(R.string.havpr_abierto) else stringResource(R.string.havpr_cerrado),
                color = MaterialTheme.customColors.textTitle54B569,
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = store.closingTime,
                color = MaterialTheme.customColors.textTitle,
                fontSize = 12.sp,
            )
        }
    }
}

// Composable que muestra la imagen de la tienda
@Composable
private fun CardStoreImage(painter: Painter) {
    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
        Image(
            painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(start = 8.dp)
                .height(90.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
        )
    }
}
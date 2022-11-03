package com.watermelon.hitoindividualaccesodatos.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomePage(homeViewModel: HomeViewModel, id: String) {
    val nombre = homeViewModel.nombre.collectAsState()
    val cc = homeViewModel.cc.collectAsState()
    val balance = homeViewModel.balance.collectAsState()
    val lista = homeViewModel.transactionList.collectAsState()
    homeViewModel.getInfo(id)
    homeViewModel.getTransacction(id)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF586BA4),
                        Color(0xFF324376)
                    )
                )
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(16.dp),
            elevation = 10.dp,
            shape = MaterialTheme.shapes.small,
            backgroundColor = Color(0xFFF5DD90)
        ) {
            Text(
                text = nombre.value,
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = balance.value.toString(),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                color = Color(0xFFA31708),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 32.dp)
            )
            Text(
                text = cc.value,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 75.dp)
            )
        }
        LazyColumn {
            items(lista.value) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 5.dp)
                        .height(60.dp)
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column() {
                            Text(text = it.tipoTransaccion, fontWeight = FontWeight.Bold)
                            Text(text = it.timeStamp)
                        }
                        Column() {
                            Text(
                                // estaria bien comprobar si es ingreso verde, retiro rojo
                                text = it.importe.toString(),
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFA31708)
                            )
                            Text(text = "")
                        }
                    }
                }
            }
        }
    }
}

package id.ac.pnm.hoventory.ui.riwayat

import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.outlined.ViewInAr
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import id.ac.pnm.hoventory.data.Riwayat
import id.ac.pnm.hoventory.ui.theme.LightGrayBg
import id.ac.pnm.hoventory.ui.theme.NavyBlue
import id.ac.pnm.hoventory.ui.theme.Purple80
import id.ac.pnm.hoventory.ui.theme.PurpleIconBg
import id.ac.pnm.hoventory.ui.theme.TextGray

@Composable
fun RiwayatScreen(
    navController: NavController,
    viewModel: RiwayatViewModel = viewModel()) {

    val riwayatList by viewModel.riwayatList.collectAsState()

    Scaffold(
        containerColor = LightGrayBg,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { },
                containerColor = NavyBlue,
                contentColor = Color.White,
                shape = RoundedCornerShape(24.dp)
            ) {
                Icon(
                    Icons.Default.Download,
                    contentDescription = "Unduh"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Unduh ke Excel",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 16.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Riwayat",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.FilterAlt,
                        contentDescription = "Filter",
                        tint = NavyBlue
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Filter",
                        color = NavyBlue,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PurpleIconBg)
                    .padding(
                        vertical = 12.dp,
                        horizontal = 20.dp
                    )
            ) {
                Text(
                    text = "Riwayat 7 hari terakhir terlihat di versi gratis",
                    color = NavyBlue,
                    fontSize = 12.sp
                )
            }
            if (riwayatList.isEmpty()) {
                EmptyStateRiwayat()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(riwayatList) { item ->
                        RiwayatItemRow(item)
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyStateRiwayat() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(NavyBlue, shape = RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ViewInAr,
                contentDescription = null,
                tint = Purple80,
                modifier = Modifier.size(64.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Tidak ada item ditemukan",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sepertinya Anda belum menambahkan apa pun. Mulai sekarang untuk melacak inventaris Anda!",
            fontSize = 14.sp,
            color = TextGray,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun RiwayatItemRow(riwayat: Riwayat) {
    val iconColor = if (riwayat.isIncoming) Color(0xFF2E7D32) else Color(0xFFD32F2F)
    val bgColor = if (riwayat.isIncoming) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
    val icon = if (riwayat.isIncoming) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(bgColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = riwayat.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                Text(text = riwayat.subtitle, fontSize = 12.sp, color = TextGray)
            }
        }
        Text(
            text = "${riwayat.amount} ${riwayat.unit}",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = iconColor
        )
    }
}



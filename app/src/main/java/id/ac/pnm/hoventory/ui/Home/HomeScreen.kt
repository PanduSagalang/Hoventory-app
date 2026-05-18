package id.ac.pnm.hoventory.ui.Home

import android.R.attr.subtitle
import android.app.Activity
import android.service.quickaccesswallet.QuickAccessWalletService
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import id.ac.pnm.hoventory.ui.riwayat.RiwayatViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import coil3.compose.AsyncImage
import id.ac.pnm.hoventory.data.Product
import id.ac.pnm.hoventory.ui.theme.GreenIconBg
import id.ac.pnm.hoventory.ui.theme.GreenText
import id.ac.pnm.hoventory.ui.theme.LightGrayBg
import id.ac.pnm.hoventory.ui.theme.NavyBlue
import id.ac.pnm.hoventory.ui.theme.PurpleIconBg
import id.ac.pnm.hoventory.ui.theme.RedIconBg
import id.ac.pnm.hoventory.ui.theme.RedText
import id.ac.pnm.hoventory.data.Riwayat
import coil3.compose.AsyncImage

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel ()
    ){
    val stokMasuk by viewModel.stokMasuk.collectAsState()
    val stokKeluar by viewModel.stokKeluar.collectAsState()
    val activities by viewModel.recentActivities.collectAsState()
    val produkList by viewModel.allProducts.collectAsState(initial = emptyList())

    var showDialogTransaksi by remember { mutableStateOf(false) }
    var isModeStokMasuk by remember { mutableStateOf(true) }
    var produkTerpilih by remember { mutableStateOf<Product?>(null) }
    var inputJumlahStok by remember { mutableStateOf("") }

    Scaffold (
        containerColor = LightGrayBg
    ){ paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ){
            TopHeader()
            SummaryCard(stokMasuk, stokKeluar)
            QuickAccessCard(
                navController = navController,
                onStokMasukClick = {
                    isModeStokMasuk = true
                    showDialogTransaksi = true
                },
                onStokKeluarClick = {
                    isModeStokMasuk = false
                    showDialogTransaksi = true
                }
            )
            RecentActivitySection(activities)
        }
        if (showDialogTransaksi) {
            AlertDialog(
                onDismissRequest = {
                    showDialogTransaksi = false
                    produkTerpilih = null
                    inputJumlahStok = ""
                },
                title = {
                    Text(
                        text = if (isModeStokMasuk) "Pilih Produk (Stok Masuk)" else "Pilih Produk (Stok Keluar)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                text = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 280.dp)
                    ) {
                        if (produkTerpilih == null) {
                            if (produkList.isEmpty()) {
                                Text("Belum ada produk terdaftar.",
                                    color = Color.Gray)
                            } else {
                                Text("Klik pada nama produk:", fontSize = 12.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(bottom = 8.dp))
                                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                                    items(produkList) { produk ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { produkTerpilih = produk }
                                                .padding(vertical = 10.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column {
                                                Text(produk.name,
                                                    fontWeight = FontWeight.SemiBold,
                                                    fontSize = 14.sp)
                                                Text("SKU: ${produk.sku}",
                                                    fontSize = 11.sp,
                                                    color = Color.Gray)
                                            }
                                            Text("Stok: ${produk.stock}",
                                                color = NavyBlue,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold)
                                        }
                                        HorizontalDivider(color = Color.LightGray,
                                            thickness = 0.5.dp)
                                    }
                                }
                            }
                        } else {
                            Text("Produk: ${produkTerpilih!!.name}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp)
                            Text("Stok saat ini: ${produkTerpilih!!.stock} ${produkTerpilih!!.baseUnit}",
                                fontSize = 12.sp,
                                color = Color.Gray)
                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = inputJumlahStok,
                                onValueChange = { inputJumlahStok = it },
                                label = { Text("Jumlah Perubahan") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                },
                confirmButton = {
                    Button(
                        enabled = produkTerpilih != null && inputJumlahStok.isNotEmpty(),
                        colors = ButtonDefaults.buttonColors(containerColor = NavyBlue),
                        onClick = {
                            val jml = inputJumlahStok.toIntOrNull() ?: 0
                            if (jml > 0 && produkTerpilih != null) {
                                if (isModeStokMasuk) {
                                    viewModel.tambahStok(produkTerpilih!!, jml)
                                } else {
                                    viewModel.kurangStok(produkTerpilih!!, jml)
                                }
                                showDialogTransaksi = false
                                produkTerpilih = null
                                inputJumlahStok = ""
                            }
                        }
                    ) {
                        Text("Save", color = Color.White)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialogTransaksi = false
                            produkTerpilih = null
                            inputJumlahStok = ""
                        }
                    ) {
                        Text("Batal", color = NavyBlue)
                    }
                }
            )
        }
    }
}

@Composable
fun TopHeader() {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "Lawu Elektronik",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = NavyBlue
        )
    }
}

@Composable
fun SummaryCard(stokMasuk: String, stokKeluar: String) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = NavyBlue),
        modifier = Modifier.fillMaxWidth()
    ){
        Spacer(modifier = Modifier.height(24.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            Column (modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(
                    "Stok Masuk",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp)
                Row {
                    Icon(Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color.Green,
                        modifier = Modifier.size(16.dp))
                    Text(
                        stokMasuk, color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
                }
            }
            HorizontalDivider(
                modifier = Modifier
                .height(40.dp)
                .width(1.dp), color = Color.White.copy(alpha = 0.2f))
            Column (
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(
                    "Stok Keluar",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp)
                Row {
                    Icon(
                        Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp))
                    Text(
                        stokKeluar,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun QuickAccessCard(
    navController: NavController,
    onStokMasukClick: () -> Unit,
    onStokKeluarClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)) {
            Text(
                "Akses Cepat",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold)
            Spacer(
                modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                QuickAccessItem(
                    "Stok Masuk",
                    Icons.AutoMirrored.Filled.ArrowForward,
                    GreenIconBg,
                    GreenText){
                    onStokMasukClick()
                }
                QuickAccessItem(
                    "Stok Keluar",
                    Icons.AutoMirrored.Filled.ArrowBack,
                    RedIconBg,
                    RedText){
                    onStokKeluarClick()
                }
                QuickAccessItem(
                    "Tambah Produk",
                    Icons.Default.Add,
                    PurpleIconBg,
                    NavyBlue){
                    navController.navigate("tambah_produk")
                }
            }
        }
    }
}

@Composable
fun QuickAccessItem(
    title: String,
    icon: ImageVector,
    bgColor: Color,
    iconColor: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier.size(64.dp).clip(RoundedCornerShape(16.dp)).background(bgColor),
            contentAlignment = Alignment.Center) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(28.dp))
        }
        Spacer(
            modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 12.sp,
            color = Color.DarkGray)
    }
}

@Composable
fun RecentActivitySection(activities: List<Riwayat>) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Aktivitas Terbaru",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold, color = NavyBlue
            )
            Text(
                "Lihat Semua",
                fontSize = 12.sp,
                color = GreenText
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        activities.forEach { item ->
            ActivityItem(riwayat = item)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

    @Composable
    fun ActivityItem(riwayat: Riwayat) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(if (riwayat.isIncoming) GreenIconBg else RedIconBg),
                    contentAlignment = Alignment.Center
                ) {
                    if (!riwayat.imageUrl.isNullOrEmpty()) {
                        AsyncImage(
                            model = riwayat.imageUrl,
                            contentDescription = "Gambar Produk",
                            modifier = Modifier.fillMaxSize().clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = if (riwayat.isIncoming) Icons.Default.Add else Icons.Default.Remove,
                            contentDescription = null,
                            tint = if (riwayat.isIncoming) GreenText else RedText
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        riwayat.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold)
                    Text(
                        riwayat.subtitle,
                        fontSize = 12.sp,
                        color = Color.Gray)
                }
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        riwayat.amount,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (riwayat.isIncoming) GreenText else RedText
                    )
                    Text(riwayat.unit,
                        fontSize = 10.sp,
                        color = Color.Gray)
                }
            }
        }
    }

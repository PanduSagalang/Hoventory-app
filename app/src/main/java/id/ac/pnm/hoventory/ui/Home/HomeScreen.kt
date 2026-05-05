package id.ac.pnm.hoventory.ui.Home

import android.app.Activity
import android.service.quickaccesswallet.QuickAccessWalletService
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.pnm.hoventory.ui.theme.GreenIconBg
import id.ac.pnm.hoventory.ui.theme.GreenText
import id.ac.pnm.hoventory.ui.theme.LightGrayBg
import id.ac.pnm.hoventory.ui.theme.NavyBlue
import id.ac.pnm.hoventory.ui.theme.PurpleIconBg
import id.ac.pnm.hoventory.ui.theme.RedIconBg
import id.ac.pnm.hoventory.ui.theme.RedText
import id.ac.pnm.hoventory.ui.theme.RedTutorial

@Composable
fun HomeScreen(){
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
            SummaryCard()
            QuickAccessCard()
            RecentActivitySeaction()
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
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = RedTutorial),
            shape = RoundedCornerShape(20.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.height(36.dp)
        ){
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Tutorial", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun SummaryCard() {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = NavyBlue),
        modifier = Modifier.fillMaxWidth()
    ){
        Spacer(modifier = Modifier.height(24.dp))

        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Column (modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally){
                Text("Stok Masuk", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                Row {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Green, modifier = Modifier.size(16.dp))
                    Text("*", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
            HorizontalDivider(modifier = Modifier
                .height(40.dp)
                .width(1.dp), color = Color.White.copy(alpha = 0.2f))
            Column (modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally){
                Text("Stok Keluar", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                Row {
                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = null, tint = Color.Red, modifier = Modifier.size(16.dp))
                    Text("*", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun QuickAccessCard() {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Akses Cepat", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                QuickAccessItem("Stok Masuk", Icons.AutoMirrored.Filled.ArrowForward, GreenIconBg, GreenText)
                QuickAccessItem("Stok Keluar", Icons.AutoMirrored.Filled.ArrowBack, RedIconBg, RedText)
                QuickAccessItem("Tambah Produk", Icons.Default.Add, PurpleIconBg, NavyBlue)
            }
        }
    }
}

@Composable
fun QuickAccessItem(title: String, icon: ImageVector, bgColor: Color, iconColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(64.dp).clip(RoundedCornerShape(16.dp)).background(bgColor), contentAlignment = Alignment.Center) {
            Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(28.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title, fontSize = 12.sp, color = Color.DarkGray)
    }
}

@Composable
fun RecentActivitySeaction() {
    Column {
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text("Aktivitas Terbaru", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            Text("Lihat Semua", fontSize = 12.sp, color = GreenText)
        }
        Spacer(modifier = Modifier.height(16.dp))

        ActivityItem("Aluminum Shims (S-12)", "09:42 AM • Marcus V.", "+250", "PCS", true)
        Spacer(modifier = Modifier.height(12.dp))
        ActivityItem("High-Tensile Bolts (H-01)", "08:15 AM • Marcus V.", "-1,200", "UNT", false)
    }
}

@Composable
fun ActivityItem(title: String, subtitle: String, amount: String, unit: String, isIncoming: Boolean) {
    Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(if (isIncoming) GreenIconBg else RedIconBg), contentAlignment = Alignment.Center) {
                Icon(if (isIncoming) Icons.Default.Add else Icons.Default.Remove, contentDescription = null, tint = if (isIncoming) GreenText else RedText)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(subtitle, fontSize = 12.sp, color = Color.Gray)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(amount, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = if (isIncoming) GreenText else RedText)
                Text(unit, fontSize = 10.sp, color = Color.Gray)
            }
        }
    }
}

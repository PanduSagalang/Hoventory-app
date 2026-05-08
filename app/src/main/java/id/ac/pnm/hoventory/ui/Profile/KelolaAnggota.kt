package id.ac.pnm.hoventory.ui.Profile

import android.R.attr.padding
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.UnfoldMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KelolaAnggota(navController: NavController? = null){Scaffold(
    topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { /* Back */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color(0xFF1A237E))
                }
            },
            title = {
                Column {
                    Text("Kelola Tim & Akses", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF1A237E))
                    Text("Lawu Elektronik", fontSize = 14.sp, color = Color.Gray)
                }
            },
            actions = {
                // Perbaikan Warna: Hijau muda sesuai gambar
                Surface(
                    color = Color(0xFFE8FFF0),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(6.dp).background(Color.Green, CircleShape))
                        Spacer(Modifier.width(4.dp))
                        Text("SYNCED", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )
    }
) { padding ->
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(padding)
    ) {
        item { TambahAnggotaCard() }

        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Groups, contentDescription = null, tint = Color(0xFF1A237E))
                    Spacer(Modifier.width(8.dp))
                    Text("Daftar Karyawan", fontWeight = FontWeight.Bold)
                }
                Surface(color = Color(0xFFF0F0F0), shape = RoundedCornerShape(12.dp)) {
                    Text("Total: 1 Orang", modifier = Modifier.padding(6.dp), fontSize = 10.sp, color = Color.Gray)
                }
            }
        }

        item {
            KaryawanItem(
                nama = "Budi Satria",
                email = "budi.satria@lawuelektronik.id",
                role = "ADMIN"
            )
        }
    }
}
}

@Composable
fun TambahAnggotaCard(){
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.PersonAdd, contentDescription = null, tint = Color(0xFF1A237E))
                Spacer(Modifier.width(8.dp))
                Text("Tambah Anggota", fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(8.dp))
            Text("Email atau Username", fontSize = 14.sp, color = Color.DarkGray)
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("name@company.com") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            Text("Peran (Role)", fontSize = 14.sp, color = Color.DarkGray)
            OutlinedTextField(
                value = "Staff",
                onValueChange = {},
                trailingIcon = { Icon(Icons.Default.UnfoldMore, null) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                readOnly = true
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A237E)),
                shape = RoundedCornerShape(12.dp)
                    ) {
                Icon(Icons.Default.AddCircleOutline, null)
                Spacer(Modifier.width(8.dp))
                Text("Kirim Undangan")
            }
        }
    }
}
@Composable
fun KaryawanItem(nama: String, email: String, role: String){
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEEEEEE))
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(50.dp).clip(RoundedCornerShape(8.dp)).background(Color.LightGray)
            )
            Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                Text(nama, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(email, color = Color.Gray, fontSize = 14.sp)
                Surface(
                    color = Color(0xFFE8EAF6),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(role, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        fontSize = 10.sp, color = Color(0xFF3F51B5), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

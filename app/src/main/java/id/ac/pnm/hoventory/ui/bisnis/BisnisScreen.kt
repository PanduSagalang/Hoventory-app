package id.ac.pnm.hoventory.ui.bisnis

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.Button
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import id.ac.pnm.hoventory.R
import id.ac.pnm.hoventory.data.Business
import id.ac.pnm.hoventory.ui.theme.NavyBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BisnisScreen(
    navController: NavController? = null,
    viewModel: BussinessViewModel = viewModel()
){
    var businessList by remember { mutableStateOf<List<Business>>(emptyList()) }
    LaunchedEffect(Unit) {
        viewModel.getUserBusinesses {
            businessList = it
        }
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Pilih Bisnis", fontWeight = FontWeight.Bold, color = Color(0xFF1A237E) ) },
            navigationIcon = {
                IconButton(onClick = { navController?.popBackStack()}){
                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color(0xFF1A237E))
                }
            },

            actions = {
                IconButton(onClick = { navController?.popBackStack() }) {
                    Icon(painterResource(id = R.drawable.ic_logo), contentDescription = null, tint = Color(0xFF1A237E))
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            ),

            modifier = Modifier.shadow(elevation = 0.dp)
        )
    }
    ) {
        padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(padding)
        ){
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment =Alignment.Center)
            {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp),
                    tint = Color.LightGray.copy(alpha = 0.2f)
                )
            }
            Column (modifier = Modifier.fillMaxSize()
                .padding(16.dp)){
                Text(
                    text = "Selamat Datang",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A237E),
                    fontSize = 18.sp
                )
                Text(
                    text = "Pilih, Buat, atau Bergabung dengan bisnis. Pilih opsi untuk melanjutkan.",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
                )

                Text(
                    text = "BISNIS ANDA",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                if (businessList.isEmpty()) {
                    Text(
                        text = "Belum ada bisnis",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                } else {
                    businessList.forEach { business ->
                        BusinessItem(
                            nama = business.name,
                            role = "Owner",
                            staffCount = 1,
                            iconRes = R.drawable.ic_logo
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            Column(modifier = Modifier.align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp))
            {
                Button(
                    onClick = { navController?.navigate("buat_bisnis")},
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A237E)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.BusinessCenter, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Buat Bisnis", color = Color.White, fontWeight = FontWeight.Bold)
                }
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = NavyBlue
                    ),
                    border = BorderStroke(1.dp,Color.LightGray)
                ) {
                    Icon(painterResource(id = R.drawable.ic_logo), contentDescription = null, tint = Color.LightGray)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Bergabung dengan Bisnis", color = Color(0xFF1A237E), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun BusinessItem(
    nama: String,
    role: String,
    staffCount: Int,
    iconRes: Int,
    iconBackgroundColor: Color = Color.White,
){
    Surface(modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp,Color.LightGray.copy(alpha = 0.5f)),
        color = Color.White)
        {
            Row(modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(iconBackgroundColor),
                    contentAlignment = Alignment.Center
                ){
                    if (iconRes != null) {
                        Image(painterResource(id = iconRes), contentDescription = null)
                    } else {
                        Icon(Icons.Default.Store, contentDescription = null, tint = Color(0xFF00796B))
                    }
                }

                Column(modifier = Modifier.weight(1f).padding(start = 12.dp)){
                    Text(text = nama, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(
                        text = "$role • $staffCount Staff",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = Color.Gray
                )

            }
        }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BisnisScreenPreview() {
    BisnisScreen()
}
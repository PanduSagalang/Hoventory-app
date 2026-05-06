package id.ac.pnm.hoventory.ui.Profile

import android.R.attr.padding
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Edit

import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController){
    Button(onClick = {
        navController.navigate("profil")
    }) {
        Text("Ke Profil")
    }
    Scaffold(
        containerColor = Color(0xFFF7F8FC),
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Hoventory",
                    color = Color.Black,
                    fontSize = 22.sp
                )
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xFFE8FFF0)
                )
                {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color.Green, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("SYNCED", fontSize = 12.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Surface(
                            modifier = Modifier.size(90.dp),
                            shape = CircleShape,
                            color = Color(0xFFE5E7EB)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    Icons.Default.Person,
                                    null,
                                    modifier = Modifier.size(50.dp),
                                    tint = Color.Gray
                                )
                            }
                        }
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFF243A8F)
                        )
                        {
                            Icon(
                                Icons.Default.Edit,
                                null,
                                tint = Color.White,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Lahap Susanto",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF243A8F)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xFFF3F4F6)
                )
                {
                    Text(
                        "STK-0842",
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 6.dp
                        ),
                        color = Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            )
            {
                Column {
                    val menus = listOf(
                        "Pengaturan Akun" to Icons.Default.Settings,
                        "Notifikasi" to Icons.Default.Notifications,
                        "Pusat Bantuan" to Icons.Default.Help,
                        "Tentang Stockly" to Icons.Default.Info
                    )
                    menus.forEach { (title, icon) ->
                        Row(
                            modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(18.dp),
                            verticalAlignment = Alignment.CenterVertically)
                        {
                            Icon(icon, null, tint = Color(0xFF243A8F))
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                title,
                                modifier = Modifier.weight(1f)
                            )

                            Icon(
                                Icons.Default.KeyboardArrowRight,
                                null,
                                tint = Color.Gray
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))

                // LOGOUT
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFEEEE),
                        contentColor = Color.Red
                    )
                ) {
                    Icon(Icons.Default.Logout, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Keluar")
                }
            }
        }
    }
}
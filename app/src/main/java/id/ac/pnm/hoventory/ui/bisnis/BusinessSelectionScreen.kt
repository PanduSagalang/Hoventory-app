package id.ac.pnm.hoventory.ui.bisnis

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.lifecycle.viewmodel.compose.viewModel
import id.ac.pnm.hoventory.ui.theme.BackgroundColor
import id.ac.pnm.hoventory.ui.theme.PrimaryBlue
import id.ac.pnm.hoventory.ui.theme.TextGray

@Composable
fun BusinessSelectionScreen(
    navController: NavController,
    viewModel: BussinessViewModel = viewModel()
) {
    var showJoinDialog by remember { mutableStateOf(false) }
    var referralCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(horizontal = 24.dp, vertical = 48.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Selamat datang di\nHoventory!",
            fontSize = 34.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            color = PrimaryBlue
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Buat bisnis baru atau gabung ke bisnis yang sudah ada.",
            fontSize = 18.sp,
            lineHeight = 28.sp,
            color = TextGray
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Pilih opsi bisnis",
            fontSize = 15.sp,
            color = TextGray,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(14.dp))
        BussinessOptionCard(
            title = "Buat Bisnis",
            description = "Mulai bisnis anda sekarang",
            icon = Icons.Default.Business,
            backgroundColor = Color(0xFFE5EEFF),
            iconColor = PrimaryBlue,
            onClick = {
                navController.navigate("buat_bisnis_register")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        BussinessOptionCard(
            title = "Gabung Bisnis",
            description = "Masuk ke bisnis yang sudah ada",
            icon = Icons.Default.Groups,
            backgroundColor = Color(0xFFDDF7E8),
            iconColor = Color(0xFF169B45),
            onClick = {
                showJoinDialog = true
            }
        )
    }
    if (showJoinDialog) {
        AlertDialog(
            onDismissRequest = {
                showJoinDialog = false
            },
            title = {
                Text(
                    text = "Gabung Bisnis",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text(
                        text = "Masukkan kode referral bisnis",
                        color = TextGray,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = referralCode,
                        onValueChange = {
                            referralCode = it
                        },
                        placeholder = {
                            Text("Contoh: AMBA69")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.joinBusiness(
                            referralCode = referralCode,
                            onSuccess = {
                                showJoinDialog = false
                                navController.navigate("login") {
                                    popUpTo(0)
                                }
                            },
                            onError = {

                            }
                        )
                    }
                ) {
                    Text(
                        "Gabung",
                        color = PrimaryBlue,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showJoinDialog = false
                    }
                ) {
                    Text("Batal")
                }
            }
        )
    }
}

@Composable
fun BussinessOptionCard(
    title: String,
    description: String,
    icon: ImageVector,
    backgroundColor: Color,
    iconColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(72.dp),
                shape = RoundedCornerShape(20.dp),
                color = backgroundColor
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    color = TextGray
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BussinessSelectionScreenPreview() {
    BusinessSelectionScreen(navController = rememberNavController())
}
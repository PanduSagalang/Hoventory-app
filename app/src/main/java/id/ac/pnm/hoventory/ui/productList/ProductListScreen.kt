package id.ac.pnm.hoventory.ui.productList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.ac.pnm.hoventory.ui.theme.BackgroundColor
import id.ac.pnm.hoventory.ui.theme.GreenIconBg
import id.ac.pnm.hoventory.ui.theme.GreenText
import id.ac.pnm.hoventory.ui.theme.PrimaryBlue
import id.ac.pnm.hoventory.ui.theme.TextGray

@Composable
fun ProductListScreen(navController: NavController) {

    var search by remember { mutableStateOf("") }

    val viewModel: ProductViewModel = viewModel()
    val products by viewModel.product.collectAsState(initial = emptyList())

    val filteredProducts = products.filter {
        it.name.contains(search, ignoreCase = true) ||
                it.sku.contains(search, ignoreCase = true)
    }

    Scaffold(
        containerColor = BackgroundColor,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("tambah_produk")
                },
                containerColor = PrimaryBlue,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, "Tambah")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Daftar Produk",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue
                )
                Row {
                    TextButton(onClick = { }) {
                        Icon(
                            Icons.Default.DeleteOutline,
                            null,
                            modifier = Modifier.size(16.dp),
                            tint = PrimaryBlue
                        )
                        Text(
                            "Hapus Massal",
                            fontSize = 11.sp,
                            color = PrimaryBlue
                        )
                    }
                    TextButton(onClick = { }) {
                        Icon(
                            Icons.Default.FileUpload,
                            null,
                            modifier = Modifier.size(16.dp),
                            tint = PrimaryBlue
                        )
                        Text(
                            "Unduh",
                            fontSize = 11.sp,
                            color = PrimaryBlue
                        )
                    }
                }
            }

            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                placeholder = { Text("Cari berdasarkan nama/SKU", fontSize = 14.sp, color = TextGray) },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Search, null, tint = TextGray) },
                trailingIcon = { Icon(Icons.Default.QrCodeScanner, null, tint = PrimaryBlue) },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = PrimaryBlue
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterButton(icon = Icons.Default.Sort,"Urutkan Daftar")
                FilterButton(icon = Icons.Default.FilterList,"Filter Daftar")
            } 

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                color = GreenIconBg,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SummaryItem(Icons.Default.Inventory2, "${filteredProducts.size} Produk")
                    SummaryItem(Icons.Default.Layers, "0 Unit")
                    SummaryItem(Icons.Default.Payments, "Rp.0,00")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(filteredProducts) { product ->
                    ProductItem(product = product)
                }
            }
        }
    }
}

@Composable
fun RowScope.FilterButton(icon: ImageVector, label: String) {
    OutlinedButton(
        onClick = { },
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(icon, null, modifier = Modifier.size(18.dp), tint = TextGray)
        Spacer(Modifier.width(4.dp))
        Text(label, fontSize = 12.sp, color = TextGray)
    }
}

@Composable
fun SummaryItem(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, modifier = Modifier.size(14.dp), tint = GreenText)
        Spacer(Modifier.width(4.dp))
        Text(text, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GreenText)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductListPreview() {
    ProductListScreen(navController = rememberNavController())
}
package id.ac.pnm.hoventory.ui.productList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import id.ac.pnm.hoventory.data.Product
import id.ac.pnm.hoventory.ui.theme.BackgroundColor

@Composable
fun ProductListScreen(navController: NavController) {

    var search by remember { mutableStateOf("") }

    val viewModel: ProductViewModel = viewModel()
    val products by viewModel.products.collectAsState()

    val filteredProducts = products.filter {
        it.name.contains(search, ignoreCase = true) ||
                it.sku.contains(search, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(16.dp)
    ) {

        Text(
            text = "Daftar Produk",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            placeholder = { Text("Cari berdasarkan nama/SKU") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { }) {
                Text("Urutkan")
            }
            Button(onClick = { }) {
                Text("Filter")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            viewModel.addProduct(
                Product(
                    id = System.currentTimeMillis().toString(),
                    sku = "NEW",
                    name = "Produk Baru",
                    category = "Test",
                    stock = 1,
                    costPrice = 5000.0
                )
            )
        }) {
            Text("Tambah Dummy")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(filteredProducts) { product ->
                ProductItem(product)
            }
        }
    }
}
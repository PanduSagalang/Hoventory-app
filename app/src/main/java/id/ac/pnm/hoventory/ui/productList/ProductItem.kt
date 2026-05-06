package id.ac.pnm.hoventory.ui.productList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.pnm.hoventory.data.Product
import id.ac.pnm.hoventory.ui.theme.GreenText
import id.ac.pnm.hoventory.ui.theme.RedText
import id.ac.pnm.hoventory.ui.theme.TextGray

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(
                    text = "SKU: ${product.sku}",
                    fontSize = 12.sp,
                    color = TextGray
                )

                Text(
                    text = "Rp. ${product.costPrice}",
                    fontSize = 13.sp,
                    color = GreenText
                )

                Text(
                    text = "Stok: ${product.stock} ${product.baseUnit}",
                    fontSize = 12.sp,
                    color = if (product.stock <= product.minStock) RedText else TextGray
                )
            }

            Button(onClick = { }) {
                Text("Opsi")
            }
        }
    }
}
package id.ac.pnm.hoventory.ui.productList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.pnm.hoventory.data.Product
import id.ac.pnm.hoventory.ui.theme.FieldBackground
import id.ac.pnm.hoventory.ui.theme.PrimaryBlue
import id.ac.pnm.hoventory.ui.theme.PurpleIconBg
import id.ac.pnm.hoventory.ui.theme.RedText
import id.ac.pnm.hoventory.ui.theme.TextGray

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(FieldBackground,RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("IMG", fontSize = 10.sp, color = TextGray)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Black
                    )

                    Surface(
                        color = PurpleIconBg,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "Dijual",
                            color = PrimaryBlue,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Rp${String.format("%,.0f", product.costPrice)}",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                    Text("  |  ", color = Color.LightGray, fontSize = 12.sp)
                    Text(
                        text = "${product.stock} ${product.baseUnit ?: "Unit"}",
                        fontSize = 12.sp,
                        color = if (product.stock <= product.minStock) RedText else TextGray
                    )
                    Text("  |  ", color = Color.LightGray, fontSize = 12.sp)
                    Text(
                        text = "Rp0,00",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                }
            }
        }
    }
}
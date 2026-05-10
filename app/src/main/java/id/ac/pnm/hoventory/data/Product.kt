package id.ac.pnm.hoventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity("product")
data class Product (
    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("id")
    val id: Int = 0,
    @field:SerializedName("sku")
    val sku: String = "",
    @field:SerializedName("name")
    val name: String = "",
    @field:SerializedName("category")
    val category: String = "",
    @field:SerializedName("base_unit")
    val baseUnit: String = "Pcs",
    @field:SerializedName("stock")
    val stock: Int = 0,
    @field:SerializedName("min_stock")
    val minStock: Int = 5,
    @field:SerializedName("cost_price")
    val costPrice: Double = 0.0,
    @field:SerializedName("image_url")
    val imageUrl: String = ""
)
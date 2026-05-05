package id.ac.pnm.hoventory.data

data class Product (
    val id: String = "",
    val sku: String = "",
    val name: String = "",
    val category: String = "",
    val baseUnit: String = "Pcs",
    val stock: Int = 0,
    val minStock: Int = 5,
    val costPrice: Double = 0.0,
    val imageUrl: String = ""
)
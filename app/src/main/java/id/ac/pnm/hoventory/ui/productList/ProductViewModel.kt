package id.ac.pnm.hoventory.ui.productList

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import id.ac.pnm.hoventory.data.Product

class ProductViewModel : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        _products.value = listOf(
            Product(
                id = "1",
                sku = "KB001",
                name = "Kabel",
                category = "Elektronik",
                baseUnit = "Pcs",
                stock = 3,
                minStock = 5,
                costPrice = 10000.0
            ),
            Product(
                id = "2",
                sku = "AD001",
                name = "Adaptor",
                category = "Elektronik",
                baseUnit = "Pcs",
                stock = 10,
                minStock = 5,
                costPrice = 25000.0
            )
        )
    }

    fun addProduct(
        sku: String,
        name: String,
        category: String,
        baseUnit: String,
        costPrice: String
    ) {
        val newProduct = Product(
            id = System.currentTimeMillis().toString(),
            sku = sku,
            name = name,
            category = category,
            baseUnit = baseUnit,
            stock = 0,
            minStock = 5,
            costPrice = costPrice.toDoubleOrNull() ?: 0.0,
            imageUrl = ""
        )
        _products.value = _products.value + newProduct
    }

    fun deleteProduct(product: Product) {
        _products.value = _products.value - product
    }
}
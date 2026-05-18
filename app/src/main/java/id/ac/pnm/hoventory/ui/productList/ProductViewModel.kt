package id.ac.pnm.hoventory.ui.productList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import id.ac.pnm.hoventory.AppDatabase
import id.ac.pnm.hoventory.data.Product
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {


    private val productDao = AppDatabase.getInstance(application).ProductDao()

    val product = productDao.getAllProducts()


    fun addProduct(
        sku: String,
        name: String,
        category: String,
        baseUnit: String,
        costPrice: String,
        imageUrl: String = ""
    ) {
        val cleanPrice = costPrice
            .replace(".","")
            .replace(",","")
            .trim()

        val newProduct = Product(
            sku = sku,
            name = name,
            category = category,
            baseUnit = baseUnit.ifEmpty { "Pcs" },
            stock = 0,
            minStock = 5,
            costPrice = cleanPrice.toDoubleOrNull() ?: 0.0,
            imageUrl = imageUrl
        )
        viewModelScope.launch {
            productDao.insert(newProduct)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            productDao.delete(product)
        }
    }
}
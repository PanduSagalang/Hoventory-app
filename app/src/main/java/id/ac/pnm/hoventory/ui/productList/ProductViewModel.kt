package id.ac.pnm.hoventory.ui.productList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import id.ac.pnm.hoventory.AppDatabase
import id.ac.pnm.hoventory.data.Product
import id.ac.pnm.hoventory.data.Riwayat
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {


    private val productDao = AppDatabase.getInstance(application).ProductDao()
    private val riwayatDao = AppDatabase.getInstance(application).RiwayatDao()

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

        val unitPilihan = baseUnit.ifEmpty { "Pcs" }

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

        val catatanRiwayat = Riwayat(
            title = name,
            subtitle = "Produk Baru Ditambahkan",
            amount = "+0",
            unit = unitPilihan,
            isIncoming = true,
            imageUrl = imageUrl
        )

        viewModelScope.launch {
            productDao.insert(newProduct)
            riwayatDao.insert(catatanRiwayat)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            productDao.delete(product)

            val catatanHapus = Riwayat(
                title = product.name,
                subtitle = "Produk Dihapus",
                amount = "0",
                unit = product.baseUnit,
                isIncoming = false,
                imageUrl = product.imageUrl
            )
            riwayatDao.insert(catatanHapus)
        }
    }
}
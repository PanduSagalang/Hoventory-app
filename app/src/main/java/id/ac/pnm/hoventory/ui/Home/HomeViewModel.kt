package id.ac.pnm.hoventory.ui.Home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import id.ac.pnm.hoventory.AppDatabase
import id.ac.pnm.hoventory.data.Product
import id.ac.pnm.hoventory.data.Riwayat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application){

    private val database = AppDatabase.getInstance(application)
    private val riwayatDao = database.RiwayatDao()
    private val productDao = database.ProductDao()

    private val _stokMasuk = MutableStateFlow("1,250")
    val stokMasuk: StateFlow<String> = _stokMasuk.asStateFlow()

    private val _stokKeluar = MutableStateFlow("420")
    val stokKeluar: StateFlow<String> = _stokKeluar.asStateFlow()

    val recentActivities : StateFlow<List<Riwayat>> = riwayatDao.getAllRiwayat()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList<Riwayat>()
        )

    val allProducts: StateFlow<List<Product>> = productDao.getAllProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList<Product>()
        )
    fun tambahStok(product: Product, jumlah: Int) {
        viewModelScope.launch {
            val stokBaru = product.stock + jumlah
            productDao.updateProductStock(product.sku, stokBaru)

            riwayatDao.insert(
                Riwayat(
                    title = product.name,
                    subtitle = "Stok Masuk Berhasil",
                    amount = "+$jumlah",
                    unit = product.baseUnit,
                    isIncoming = true,
                    imageUrl = product.imageUrl
                )
            )
        }
    }

    fun kurangStok(product: Product, jumlah: Int) {
        viewModelScope.launch {
            val stokBaru = product.stock - jumlah
            productDao.updateProductStock(product.sku, stokBaru)

            riwayatDao.insert(
                Riwayat(
                    title = product.name,
                    subtitle = "Stok Keluar Berhasil",
                    amount = "-$jumlah",
                    unit = product.baseUnit,
                    isIncoming = false,
                    imageUrl = product.imageUrl
                )
            )
        }
    }
}


package id.ac.pnm.hoventory.ui.Home

import androidx.lifecycle.ViewModel
import id.ac.pnm.hoventory.data.Riwayat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel(){

    private val _stokMasuk = MutableStateFlow("1,250")
    val stokMasuk: StateFlow<String> = _stokMasuk.asStateFlow()

    private val _stokKeluar = MutableStateFlow("420")
    val stokKeluar: StateFlow<String> = _stokKeluar.asStateFlow()

    private val _recentActivities = MutableStateFlow<List<Riwayat>>(emptyList())
    val recentActivities: StateFlow<List<Riwayat>> = _recentActivities.asStateFlow()

    init {
        loadDummyData()
    }

    private fun loadDummyData(){
        _recentActivities.value = listOf(
            Riwayat("Kabel Tembaga 2mm", "09:42 AM • Pandu S.", "+250", "Roll", true),
            Riwayat("Lampu LED Philips 15W", "08:15 AM • Dhea", "-120", "PCS", false),
            Riwayat("Stop Kontak Broco", "Kemarin • Admin", "+50", "Box", true)
        )
    }
}
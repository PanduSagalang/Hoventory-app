package id.ac.pnm.hoventory.ui.riwayat

import androidx.lifecycle.ViewModel
import id.ac.pnm.hoventory.data.Riwayat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RiwayatViewModel: ViewModel() {

    private val _riwayatList = MutableStateFlow<List<Riwayat>>(emptyList())
    val riwayatList: StateFlow<List<Riwayat>> = _riwayatList.asStateFlow()

}

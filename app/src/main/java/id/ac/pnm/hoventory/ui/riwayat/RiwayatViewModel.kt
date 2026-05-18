package id.ac.pnm.hoventory.ui.riwayat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.pnm.hoventory.AppDatabase
import id.ac.pnm.hoventory.data.Riwayat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class RiwayatViewModel(application: Application) : AndroidViewModel(application) {

    private val riwayatDao = AppDatabase.getInstance(application).RiwayatDao()
    val riwayatList: StateFlow<List<Riwayat>> = riwayatDao.getAllRiwayat()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList<Riwayat>()
        )

}

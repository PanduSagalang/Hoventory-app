package id.ac.pnm.hoventory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.pnm.hoventory.data.Riwayat
import kotlinx.coroutines.flow.Flow

@Dao
interface RiwayatDao {

    @Insert
    suspend fun insert(riwayat: Riwayat)

    @Query("SELECT * FROM riwayat_table ORDER BY timestamp DESC")
    fun getAllRiwayat(): Flow<List<Riwayat>>

    @Query("DELETE FROM riwayat_table")
    suspend fun deleteAll()
}
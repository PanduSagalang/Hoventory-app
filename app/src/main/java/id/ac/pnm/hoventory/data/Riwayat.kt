package id.ac.pnm.hoventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "riwayat_table")
data class Riwayat (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val subtitle: String,
    val amount: String,
    val unit: String,
    val isIncoming: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val imageUrl: String
)
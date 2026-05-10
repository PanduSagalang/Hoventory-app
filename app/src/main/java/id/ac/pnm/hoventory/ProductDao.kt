package id.ac.pnm.hoventory

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import id.ac.pnm.hoventory.data.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
@Insert
suspend fun insert(vararg product: Product)

@Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<Product>>


@Delete
suspend fun delete(product: Product)
}
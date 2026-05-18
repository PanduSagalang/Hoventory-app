package id.ac.pnm.hoventory.ui.bisnis

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class BuatBisnisViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    fun buatBisnis(
        namaBisnis: String,
        negara: String,
        alamat: String,
        email: String,
        telepon: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            onError("User tidak ditemukan")
            return
        }
        val businessId = database.child("business").push().key ?: return
        val referralCode = UUID.randomUUID()
            .toString()
            .substring(0,6)
            .uppercase()

        val businessData = mapOf(
            "businessId" to businessId,
            "name" to namaBisnis,
            "country" to negara,
            "address" to alamat,
            "email" to email,
            "phone" to telepon,
            "ownerId" to uid,
            "referralCode" to referralCode
        )

        database.child("business")
            .child(businessId)
            .setValue(businessData)
            .addOnSuccessListener {
                database.child("users")
                    .child(uid)
                    .child("businessId")
                    .setValue(businessId)

                database.child("business_members")
                    .child(businessId)
                    .child(uid)
                    .child("role")
                    .setValue("Owner")
                onSuccess()
            }
            .addOnFailureListener {
                onError(it.message ?: "Gagal membuat bisnis")
            }
    }
}
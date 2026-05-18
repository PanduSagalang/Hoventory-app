package id.ac.pnm.hoventory.ui.bisnis

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import id.ac.pnm.hoventory.data.Business

class BussinessViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    fun joinBusiness(
        referralCode: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val uid = auth.currentUser?.uid ?: return
        database.child("business")
            .get()
            .addOnSuccessListener { snapshot ->
            var found = false
            snapshot.children.forEach{ business ->
                val code = business.child("referralCode").getValue(String::class.java)

                if (code == referralCode) {
                    found = true
                    val bussinessId = business.key ?: ""
                    database.child("users")
                        .child(uid)
                        .child("businessId")
                        .setValue(bussinessId)

                    database.child("business_members")
                        .child(bussinessId)
                        .child(uid)
                        .child("role")
                        .setValue("Anggota")
                    onSuccess()
                }
            }
            if (!found) {
                onError("Kode referral tidak ditemukan")
            }
        }
            .addOnFailureListener {
                onError(it.message ?: "Terjadi Kesalahan")
            }
    }

    fun getUserBusinesses(
        onResult: (List<Business>) -> Unit
    ) {
        val uid = auth.currentUser?.uid ?: return
        database.child("business_members")
            .get()
            .addOnSuccessListener { memberSnapshot ->
                val businessIds = mutableListOf<String>()
                memberSnapshot.children.forEach { business ->
                    if (business.child(uid).exists()) {
                        business.key?.let {
                            businessIds.add(it)
                        }
                    }
                }
                database.child("business")
                    .get()
                    .addOnSuccessListener { businessSnapshot ->
                        val businesses = mutableListOf<Business>()
                        businessSnapshot.children.forEach { item ->
                            val business = item.getValue(Business::class.java)
                            if (
                                business != null &&
                                business.businessId in businessIds
                            ) {
                                businesses.add(business)
                            }
                        }
                        onResult(businesses)
                    }
            }
    }
}
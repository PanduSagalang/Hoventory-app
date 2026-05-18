package id.ac.pnm.hoventory.ui.bisnis

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class BussinessViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    fun joinBusiness(
        referralCode: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val uid = auth.currentUser?.uid ?: return
        database.child("business").get().addOnSuccessListener { snapshot ->
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

                    database.child("business_member")
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
    }
}
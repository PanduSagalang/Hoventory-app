package id.ac.pnm.hoventory.ui.Profile

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    private val _nama = MutableStateFlow("")
    val nama: StateFlow<String> = _nama
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    init {
        getUserData()
        _email.value = auth.currentUser?.email ?: ""
    }

    private fun getUserData() {
        val uid = auth.currentUser?.uid ?: return

        database.child("users")
            .child(uid)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val namaFirebase = snapshot.child("nama").getValue(String::class.java)
                    _nama.value = namaFirebase ?: ""
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    fun logout() {
        auth.signOut()
    }
}
package id.ac.pnm.hoventory.ui.register

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel: ViewModel() {
    private val _nama = MutableStateFlow("")
    val nama: StateFlow<String> = _nama
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password
    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _registerResult = MutableStateFlow<String?>(null)
    val registerResult: StateFlow<String?> = _registerResult

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    fun onNamaChange(value: String) {
        _nama.value = value
    }
    fun onEmailChange(value: String) {
        _email.value = value
    }
    fun onPasswordChange(value: String) {
        _password.value = value
    }
    fun onConfirmPasswordChange(value: String) {
        _confirmPassword.value = value
    }

    fun register() {
        if (
            nama.value.isBlank() ||
            email.value.isBlank() ||
            password.value.isBlank() ||
            confirmPassword.value.isBlank()
        ) {
            _registerResult.value = "Semua field harus diisi"
            return
        }
        if (password.value != confirmPassword.value) {
            _registerResult.value = "Password tidak sama"
            return
        }
        if (password.value.length < 8) {
            _registerResult.value = "Password minimal 8 karakter"
            return
        }

        _isLoading.value = true
        auth.createUserWithEmailAndPassword(
            email.value,
            password.value
        )
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid ?: ""
                    val userData = mapOf(
                        "uid" to uid,
                        "nama" to nama.value,
                        "email" to email.value
                    )
                    database.child("users")
                        .child(uid)
                        .setValue(userData)
                        .addOnSuccessListener {
                            _isLoading.value = false
                            _registerResult.value = "SUCCESS"
                        }
                        .addOnFailureListener {
                            _isLoading.value = false
                            _registerResult.value = it.message
                        }
                } else {
                    _isLoading.value = false
                    _registerResult.value = task.exception?.message
                }
            }
    }
    fun clearRegisterResult() {
        _registerResult.value = null
    }
}
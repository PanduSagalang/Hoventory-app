package id.ac.pnm.hoventory.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _loginResult = MutableStateFlow<String?>(null)
    val loginResult: StateFlow<String?> = _loginResult
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }
    fun login() {
        viewModelScope.launch {
            _isLoading.value = true

            val email = _email.value
            val password = _password.value

            if (email.isEmpty() || password.isEmpty()) {
                _loginResult.value = "Email dan password tidak boleh kosong"
                _isLoading.value = false
                return@launch
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    _isLoading.value = false

                    if (task.isSuccessful) {
                        _loginResult.value = "SUCCESS"
                    } else {
                        _loginResult.value = task.exception?.message
                            ?: "Login gagal"
                    }
                }
        }
    }
    fun clearLoginResult() {
        _loginResult.value = null
    }
}
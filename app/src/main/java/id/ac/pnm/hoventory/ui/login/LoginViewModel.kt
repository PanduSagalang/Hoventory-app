package id.ac.pnm.hoventory.ui.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
        val emailValue = email.value.trim()
        val passwordValue = password.value

        if (
            emailValue.isBlank() ||
            passwordValue.isBlank()
        ) {
            _loginResult.value =
                "Email dan password tidak boleh kosong"
            return
        }

        if (
            !android.util.Patterns.EMAIL_ADDRESS
                .matcher(emailValue)
                .matches()
        ) {
            _loginResult.value =
                "Format email tidak valid"
            return
        }

        _isLoading.value = true
        auth.signInWithEmailAndPassword(
            emailValue,
            passwordValue
        )
            .addOnCompleteListener { task ->
                _isLoading.value = false

                if (task.isSuccessful) {
                    _loginResult.value = "SUCCESS"
                } else {
                    _loginResult.value =
                        task.exception?.message
                            ?: "Login gagal"
                }
            }
    }
    fun resetPassword() {
        val emailValue = email.value.trim()

        if (emailValue.isBlank()) {
            _loginResult.value = "Masukkan email terlebih dahulu"
            return
        }
        if (
            !android.util.Patterns.EMAIL_ADDRESS
                .matcher(emailValue)
                .matches()
        ) {
            _loginResult.value = "Format email tidak valid"
            return
        }

        auth.sendPasswordResetEmail(emailValue)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginResult.value = "Link reset password telah dikirim ke email"
                } else {
                    _loginResult.value = task.exception?.message ?: "Gagal mengirim reset password"
                }
            }
    }
    fun clearLoginResult() {
        _loginResult.value = null
    }
}
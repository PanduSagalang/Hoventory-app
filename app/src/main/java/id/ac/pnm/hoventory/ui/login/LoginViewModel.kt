package id.ac.pnm.hoventory.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
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

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun login() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000)

            if (_email.value == "user@gmail.com" && _password.value == "12345") {
                _loginResult.value = "SUCCESS"
            } else {
                _loginResult.value = "Email atau password salah"
            }

            _isLoading.value = false
        }
    }

    fun clearLoginResult() {
        _loginResult.value = null
    }
}
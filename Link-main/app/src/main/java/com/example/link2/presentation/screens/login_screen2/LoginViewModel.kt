package com.example.link2.presentation.screens.login_screen2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.link2.data.repository.Repository
import com.example.link2.utils.validation_utils.ValidationUtils
import com.example.link2.utils.validation_utils.ValidationUtils.isValidEmail
import com.example.link2.utils.validation_utils.ValidationUtils.isValidPassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: Repository) : ViewModel()
{

    //region Onclick methods
    fun onLoginClickedUsingConditional() {
        viewModelScope.launch {
            try {
                val emailValue = email.value?.trim()
                val passwordValue = password.value?.trim()
                if (!emailValue.isNullOrEmpty() && !passwordValue.isNullOrEmpty()) {
                    val result = repository.emailPasswordLogin(emailValue, passwordValue)
                    if (result == null) {
                        _emailError.value = "Invalid credentials"
                    } else {
                        // Navigate /handle successful login
                    }
                } else {
                    if (emailValue.isNullOrEmpty()) {
                        _emailError.value = "Email is missing"
                    }
                    if (passwordValue.isNullOrEmpty()) {
                        _passwordError.value = "Password is missing"
                    }
                }
            } catch (e: Exception) {
                _emailError.value = e.localizedMessage ?: "An error occurred"
            }
        }
    }

    fun onLoginClickedUsingLet() {
        viewModelScope.launch {
            try {
                val emailValue = email.value
                val passwordValue = password.value

                emailValue?.takeIf { it.isNotEmpty() }?.let { eValue ->
                    passwordValue?.takeIf { it.isNotEmpty() }?.let { pValue ->
                        val result = repository.emailPasswordLogin(eValue, pValue)
                        if (result == null) {
                            _emailError.value = "Invalid credentials"
                        } else {

                            println(repository.currentUser)
                        }
                    } ?: run {
                        _passwordError.value = "Password is missing"
                    }
                } ?: run {
                    _emailError.value = "Email is missing"
                }
            } catch (e: Exception) {
                _emailError.value = e.localizedMessage ?: "An error occurred"
            }
        }
    }
    //endregion



    //region Livedata objects
    private val _email = MutableStateFlow<String>("")
    val email: MutableStateFlow<String> get() = _email

    private val _password = MutableStateFlow<String>("")
    val password: MutableStateFlow<String> get() = _password

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: MutableStateFlow<String?> get() = _emailError

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: MutableStateFlow<String?> get() = _passwordError
    //endregion

    fun onEmailChanged(newEmail: String)
    {
        _email.value = newEmail
        _emailError.value =
            if (!isValidEmail(newEmail) && newEmail.isNotEmpty()) "Invalid email" else null
    }

    fun onPasswordChanged(newPassword: String)
    {

        _passwordError.value = null  // Clear the previous error first

        _password.value = newPassword
        _passwordError.value =
            if (!isValidPassword(newPassword) && newPassword.isNotEmpty()) ValidationUtils.passwordErrorText else null

    }


}

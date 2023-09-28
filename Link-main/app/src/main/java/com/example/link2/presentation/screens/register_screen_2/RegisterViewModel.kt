package com.example.link2.presentation.screens.register_screen_2

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.link2.data.repository.Repository
import com.example.link2.presentation.navigation.Screen
import com.example.link2.utils.validation_utils.ValidationUtils
import com.example.link2.utils.validation_utils.ValidationUtils.dateOfBirthErrorText
import com.example.link2.utils.validation_utils.ValidationUtils.emailErrorText
import com.example.link2.utils.validation_utils.ValidationUtils.firstNameErrorText
import com.example.link2.utils.validation_utils.ValidationUtils.lastNameErrorText
import com.example.link2.utils.validation_utils.ValidationUtils.passwordErrorText
import com.example.link2.utils.validation_utils.ValidationUtils.registrationErrorText
import com.example.link2.utils.validation_utils.ValidationUtils.registrationErrorText2
import com.example.link2.utils.validation_utils.ValidationUtils.registrationSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val repository: Repository)
{
    //region Livedata Objects
    private val _firstName = MutableStateFlow<String>("")
    val firstName: MutableStateFlow<String> get() = _firstName

    private val _lastName = MutableStateFlow<String>("")
    val lastName: MutableStateFlow<String> get() = _lastName

    private val _email = MutableStateFlow<String>("")
    val email: MutableStateFlow<String> get() = _email

    private val _password = MutableStateFlow<String>("")
    val password: MutableStateFlow<String> get() = _password

    private val _dateOfBirth = MutableStateFlow<LocalDate>(LocalDate.now().minusYears(18))
    val dateOfBirth: MutableStateFlow<LocalDate> get() = _dateOfBirth

    private val _selectedGender = MutableStateFlow<ValidationUtils.Gender?>(null)
    val selectedGender: MutableStateFlow<ValidationUtils.Gender?> get() = _selectedGender

    private val _agreeToTerms = MutableStateFlow<Boolean>(false)
    val agreeToTerms: MutableStateFlow<Boolean> get() = _agreeToTerms

    private val _registrationResult = MutableStateFlow<String?>(null)
    val registrationResult: MutableStateFlow<String?> get() = _registrationResult


    private val _firstNameError = MutableStateFlow<String?>(null)
    val firstNameError: MutableStateFlow<String?> get() = _firstNameError

    private val _lastNameError = MutableStateFlow<String?>(null)
    val lastNameError: MutableStateFlow<String?> get() = _lastNameError

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: MutableStateFlow<String?> get() = _emailError

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: MutableStateFlow<String?> get() = _passwordError

    private val _dateOfBirthError = MutableStateFlow<String?>(null)
    val dateOfBirthError: MutableStateFlow<String?> get() = _dateOfBirthError



    //endregion

    //region Observable Updater Functions
    fun onFirstNameChange(newFirstName: String)
    {
        _firstName.value = newFirstName
        _firstNameError.value =
            if (!ValidationUtils.isValidFirstName(newFirstName) && newFirstName.isNotEmpty()) firstNameErrorText else null
    }

    fun onLastNameChange(newLastName: String)
    {
        _lastName.value = newLastName
        _lastNameError.value =
            if (!ValidationUtils.isValidLastName(newLastName) && newLastName.isNotEmpty()) lastNameErrorText else null
    }

    fun onEmailChanged(newEmail: String)
    {
        _email.value = newEmail
        _emailError.value =
            if (!ValidationUtils.isValidEmail(newEmail) && newEmail.isNotEmpty()) emailErrorText else null
    }

    fun onPasswordChanged(newPassword: String)
    {

        _passwordError.value = null  // Clear the previous error first

        _password.value = newPassword
        _passwordError.value =
            if (!ValidationUtils.isValidPassword(newPassword) && newPassword.isNotEmpty()) passwordErrorText else null

    }

    fun onDateOfBirthChanged(newDateOfBirth: LocalDate)
    {
        _dateOfBirth.value = newDateOfBirth
        val age = Period.between(newDateOfBirth, LocalDate.now()).years
        if (age < 18) {
            _dateOfBirthError.value = dateOfBirthErrorText
        } else {
            _dateOfBirthError.value = null
        }
    }

    fun onGenderChanged(newGender: ValidationUtils.Gender)
    {
        _selectedGender.value = newGender
    }

    fun onAgreeToTermsChanged(newAgreeToTerms: Boolean)
    {
        _agreeToTerms.value = newAgreeToTerms
    }

    suspend fun registerUser(navController: NavController)
    {
        if (_firstName.value.isBlank() || _lastName.value.isBlank() || _email.value.isBlank() || _password.value.isBlank() || _dateOfBirth.value == null || _selectedGender.value == null || !_agreeToTerms.value) {
            _registrationResult.value = registrationErrorText
            return
        }

        val user = repository.createEmailPasswordAccount(
            firstName = _firstName.value.trim(),
            lastName = _lastName.value.trim(),
            email = _email.value.trim(),
            password = _password.value.trim()
        )

        if (user != null) {
            _registrationResult.value = registrationSuccess

            navController.navigate(Screen.DashboardScreen)


            // Clear the fields after a successful registration
            _firstName.value = ""
            _lastName.value = ""
            _email.value = ""
            _password.value = ""
            _dateOfBirth.value = (LocalDate.now().minusYears(18))
            _selectedGender.value = null
            _agreeToTerms.value = false






        }  else {
            _registrationResult.value = registrationErrorText2
        }
    }
    //endregion




}
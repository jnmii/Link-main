package com.example.link2.utils.validation_utils

object ValidationUtils
{

    private const val emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    private const val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=]).{8,}$"

    fun isValidEmail(email: String): Boolean
    {
        return email.matches(emailPattern.toRegex())
    }

    fun isValidPassword(password: String): Boolean
    {
        return password.matches(passwordPattern.toRegex())
    }

    fun isValidFirstName(firstName: String): Boolean
    {
        return firstName.length > 1
    }

    fun isValidLastName(lastName: String): Boolean
    {
        return lastName.length > 1
    }

    val passwordErrorText = """
Password must meet the following criteria:
- At least 8 characters long.
- Contain at least one uppercase letter.
- Contain at least one lowercase letter.
- Include at least one digit.
- Have at least one special character (e.g., @, #, $, %, ^, &, +, =).
"""

    val firstNameErrorText = "Please enter a first name"

    val lastNameErrorText = "Please enter a last name"

    val emailErrorText = "Invalid Email"

    val dateOfBirthErrorText = "You must be at least 18 years old to register."

    val registrationErrorText = "Please fill in all fields and agree to the terms and conditions."

    val registrationSuccess = "Registration successful!"

    val registrationErrorText2 =  "Registration failed. Please try again."

    // Any additional validation methods can be added here

    enum class Gender {
        Male, Female, Other
    }


}

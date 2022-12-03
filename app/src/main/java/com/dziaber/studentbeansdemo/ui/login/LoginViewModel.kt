package com.dziaber.studentbeansdemo.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dziaber.studentbeansdemo.R
import com.dziaber.studentbeansdemo.data.LoginRepository
import com.dziaber.studentbeansdemo.data.Result

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(email: String, password: String) {
        val result = loginRepository.login(email, password)

        if (result is Result.Success) {
            _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(email: String, password: String) {
        if (!isEmailValid(email)) {
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        } else {
            val errorEnumSet = checkValidity(password)
            if(errorEnumSet.contains(PasswordErrors.TOO_SHORT))
                _loginForm.value = LoginFormState(passwordError = R.string.invalid_password_too_short)
            else if (errorEnumSet.count()<2)
                _loginForm.value = LoginFormState(isDataValid = true)
            else
                _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return email.isNotBlank()
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
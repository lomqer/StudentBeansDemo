package com.dziaber.studentbeansdemo.ui.login

data class LoginResult(
        val success: LoggedInUserView? = null,
        val error: Int? = null
)
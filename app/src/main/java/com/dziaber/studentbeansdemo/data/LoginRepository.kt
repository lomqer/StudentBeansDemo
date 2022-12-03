package com.dziaber.studentbeansdemo.data

import com.dziaber.studentbeansdemo.data.model.LoggedInUser

/**
 * A class managing the logging the user in through a provided [dataSource]
 */
class LoginRepository(val dataSource: LoginDataSource) {

    var user: LoggedInUser? = null
        private set

    init {
        user = null
    }

    fun login(email: String, password: String): Result<LoggedInUser> {
        val result = dataSource.login(email, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }
}
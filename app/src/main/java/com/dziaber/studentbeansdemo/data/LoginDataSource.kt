package com.dziaber.studentbeansdemo.data

import com.dziaber.studentbeansdemo.data.model.LoggedInUser
import java.io.IOException

/**
 * A mock up of a data source used for logging the user in
 */
class LoginDataSource {
    /**
     * Returns a fake login *Result* using a given email unto @ as username.
     */
    fun login(email: String, password: String): Result<LoggedInUser> {
        return try {
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), email.substring(0,email.indexOf('@')))
            Result.Success(fakeUser)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }
}